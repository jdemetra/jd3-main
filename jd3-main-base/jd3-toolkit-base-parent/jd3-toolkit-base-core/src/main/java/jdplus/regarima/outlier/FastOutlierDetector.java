/*
 * Copyright 2016 National Bank of Belgium
 * 
 * Licensed under the EUPL, Version 1.1 or – as soon they will be approved 
 * by the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and 
 * limitations under the Licence.
 */
package jdplus.regarima.outlier;

import jdplus.stats.RobustStandardDeviationComputer;
import jdplus.arima.IArimaModel;
import jdplus.arima.StationaryTransformation;
import jdplus.arima.estimation.FastKalmanFilter;
import jdplus.data.DataBlock;
import jdplus.stats.likelihood.ConcentratedLikelihoodWithMissing;
import jdplus.math.linearfilters.BackFilter;
import jdplus.math.linearfilters.RationalBackFilter;
import jdplus.math.polynomials.Polynomial;
import jdplus.regarima.RegArmaModel;
import jdplus.regarima.estimation.ConcentratedLikelihoodComputer;
import jdplus.modelling.regression.IOutlierFactory;
import demetra.data.DoubleSeq;
import jdplus.stats.likelihood.Likelihood;

/**
 *
 * @author Jean Palate
 * @param <T>
 */
public class FastOutlierDetector<T extends IArimaModel> extends
        SingleOutlierDetector<T> {

    private double[] el;
    private IArimaModel stmodel;
    private BackFilter ur;
    private double mad;

    /**
     *
     * @param computer
     */
    public FastOutlierDetector(RobustStandardDeviationComputer computer) {
        super(computer == null ? RobustStandardDeviationComputer.mad() : computer);
    }

    /**
     *
     * @return
     */
    @Override
    protected boolean calc() {
        if (getOutlierFactoriesCount() == 0 || ubound <= lbound) {
            return false;
        }
        if (!initmodel()) {
            return false;
        }
        for (int i = 0; i < getOutlierFactoriesCount(); ++i) {
            processOutlier(i);
        }

        return true;
    }

    /**
     *
     * @param all
     */
    @Override
    protected void clear(boolean all) {
        super.clear(all);
        el = null;
    }

    private boolean initmodel() {
        StationaryTransformation<IArimaModel> st = getRegArima().arima().stationaryTransformation();
        stmodel = st.getStationaryModel();
        ur = st.getUnitRoots();
        ConcentratedLikelihoodWithMissing cll = ConcentratedLikelihoodComputer.DEFAULT_COMPUTER.compute(getRegArima());
        DoubleSeq residuals = fullResiduals(getRegArima().differencedModel(), cll);
        el = residuals.toArray();
        mad = getStandardDeviationComputer().compute(residuals);
        return true;
    }

    private void processOutlier(int idx) {
        int nl = el.length;
        int d = ur.getDegree();
        int n = nl + d;
//        double[] o = new double[n];
//        DataBlock O = new DataBlock(o);
        IOutlierFactory.FilterRepresentation representation = getOutlierFactory(idx).getFilterRepresentation();
        if (representation == null) {
            return;
        }
        IArimaModel model = getRegArima().arima();
        RationalBackFilter pi = model.getPiWeights();
        double[] o = pi.times(representation.filter).getWeights(n);
        double corr = 0;
        if (d == 0 && representation.correction != 0) {
            Polynomial ar = model.getAr().asPolynomial();
            Polynomial ma = model.getMa().asPolynomial();
            corr = representation.correction * ar.evaluateAt(1) / ma.evaluateAt(1);
            for (int i = 0; i < n; ++i) {
                o[i] += corr;
            }
        }

        // o contains the filtered outlier
        // we start at the end
        //double maxval = 0;
        double sxx = 0;
        if (corr != 0) {
            sxx = corr * corr * nl;
        }

        int lb = getLBound(), ub = getUBound();
        for (int ix = 0; ix < n; ++ix) {
            sxx += o[ix] * o[ix];
            if (corr != 0) {
                sxx -= corr * corr;
            }
            int kmax = ix + 1;
            if (kmax > nl) {
                kmax = nl;
                sxx -= o[ix - nl] * o[ix - nl];
                if (corr != 0) {
                    sxx += corr * corr;
                }
            }
            double sxy = 0;
            for (int k = 0, ek = nl - 1; k < kmax; ++k, --ek) {
                sxy += el[ek] * o[ix - k];
            }
            if (corr != 0) {
                double cxy = 0;
                for (int k = 0; k < nl - kmax; ++k) {
                    cxy += el[k];
                }
                sxy += cxy * corr;
            }
            int pos = n - 1 - ix;
            if (isAllowed(pos, idx) && pos >= lb && pos < ub) {
                double c = sxy / sxx;
                double val = c * Math.sqrt(sxx) / mad;
                setCoefficient(pos, idx, c);
                setT(pos, idx, val);
            }
        }
    }

    private DoubleSeq fullResiduals(RegArmaModel<T> differencedModel, ConcentratedLikelihoodWithMissing cll) {
        if (cll.nx() == 0) {
            return cll.e();
        }
        DataBlock res = differencedModel.asLinearModel().calcResiduals(cll.allCoefficients());
        FastKalmanFilter filter = new FastKalmanFilter(stmodel);
        Likelihood ll = filter.process(res);
        return ll.e();
    }
}
