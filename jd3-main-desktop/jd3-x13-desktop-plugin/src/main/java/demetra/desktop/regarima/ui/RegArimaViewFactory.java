/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demetra.desktop.regarima.ui;

import demetra.desktop.processing.ui.modelling.ForecastsFactory;
import demetra.desktop.processing.ui.modelling.InputFactory;
import demetra.desktop.processing.ui.modelling.LikelihoodFactory;
import demetra.desktop.processing.ui.modelling.ModelArimaFactory;
import demetra.desktop.processing.ui.modelling.ModelRegressorsFactory;
import demetra.desktop.processing.ui.modelling.NiidTestsFactory;
import demetra.desktop.processing.ui.modelling.OutOfSampleTestFactory;
import demetra.desktop.processing.ui.modelling.RegSarimaViews;
import demetra.desktop.ui.processing.GenericTableUI;
import demetra.desktop.ui.processing.HtmlItemUI;
import demetra.desktop.ui.processing.IProcDocumentItemFactory;
import demetra.desktop.ui.processing.IProcDocumentViewFactory;
import demetra.desktop.ui.processing.ProcDocumentItemFactory;
import demetra.desktop.ui.processing.ProcDocumentViewFactory;
import demetra.desktop.ui.processing.stats.ResidualsDistUI;
import demetra.desktop.ui.processing.stats.ResidualsUI;
import demetra.desktop.ui.processing.stats.SpectrumUI;
import demetra.html.HtmlElement;
import demetra.information.InformationSet;
import demetra.modelling.ModellingDictionary;
import demetra.modelling.SeriesInfo;
import demetra.timeseries.TsData;
import demetra.timeseries.TsDocument;
import demetra.util.Id;
import demetra.x13.io.information.RegArimaSpecMapping;
import jdplus.x13.regarima.RegArimaDocument;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import jdplus.regsarima.regular.RegSarimaModel;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Jean Palate
 */
public class RegArimaViewFactory extends ProcDocumentViewFactory<RegArimaDocument> {

    private static final AtomicReference<IProcDocumentViewFactory<RegArimaDocument>> INSTANCE = new AtomicReference();

    private final static Function<RegArimaDocument, RegSarimaModel> MODELEXTRACTOR= doc->doc.getResult();

    private final static Function<RegArimaDocument, TsData> RESEXTRACTOR = doc ->{ 
        RegSarimaModel result = doc.getResult();
        return result == null ? null : result.fullResiduals();
                    };

        public static IProcDocumentViewFactory<RegArimaDocument> getDefault() {
        IProcDocumentViewFactory<RegArimaDocument> fac = INSTANCE.get();
        if (fac == null){
            fac=new RegArimaViewFactory();
            INSTANCE.lazySet(fac);
        }
        return fac;
    }

    public static void setDefault(IProcDocumentViewFactory<RegArimaDocument> factory) {
        INSTANCE.set(factory);
    }

    public RegArimaViewFactory() {
        registerFromLookup(RegArimaDocument.class);
    }

    @Override
    public Id getPreferredView() {
        return RegSarimaViews.MODEL_SUMMARY;
    }
    

//<editor-fold defaultstate="collapsed" desc="REGISTER SPEC">
    @ServiceProvider(service = IProcDocumentItemFactory.class, position = 100010)
    public static class SpecFactory extends ProcDocumentItemFactory<RegArimaDocument, HtmlElement> {

        public SpecFactory() {
            super(RegArimaDocument.class, RegSarimaViews.INPUT_SPEC,
                    (RegArimaDocument doc) -> {
                        InformationSet info = RegArimaSpecMapping.write(doc.getSpecification(), doc.getInput().getData().getDomain(), true);
                        return new demetra.html.core.HtmlInformationSet(info);
                    },
                    new HtmlItemUI()
            );
        }

        @Override
        public int getPosition() {
            return 100010;
        }
    }

    @ServiceProvider(service = IProcDocumentItemFactory.class, position = 100000)
    public static class Input extends InputFactory<RegArimaDocument> {

        public Input() {
            super(RegArimaDocument.class, RegSarimaViews.INPUT_SERIES);
        }

        @Override
        public int getPosition() {
            return 100000;
        }
    }

//</editor-fold>
//
//<editor-fold defaultstate="collapsed" desc="REGISTER SUMMARY">
    @ServiceProvider(service = IProcDocumentItemFactory.class, position = 100000 + 1000)
    public static class SummaryFactory extends ProcDocumentItemFactory<RegArimaDocument, HtmlElement> {

        public SummaryFactory() {
            super(RegArimaDocument.class, RegSarimaViews.MODEL_SUMMARY,
            source->new demetra.html.modelling.HtmlRegSarima(source.getResult(), false),
            new HtmlItemUI());
        }

        @Override
        public int getPosition() {
            return 101000;
        }
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="REGISTER FORECASTS">
    @ServiceProvider(service = IProcDocumentItemFactory.class, position = 200000 + 500)
    public static class ForecastsTable extends ProcDocumentItemFactory<RegArimaDocument, TsDocument> {

        public ForecastsTable() {
            super(RegArimaDocument.class, RegSarimaViews.MODEL_FCASTS_TABLE, s -> s, new GenericTableUI(false, generateItems()));
        }

        @Override
        public int getPosition() {
            return 200500;
        }

        private static String[] generateItems() {
            return new String[]{ModellingDictionary.Y + SeriesInfo.F_SUFFIX, ModellingDictionary.Y + SeriesInfo.EF_SUFFIX};
        }

    }

    @ServiceProvider(service = IProcDocumentItemFactory.class, position = 200000 + 1000)
    public static class FCastsFactory extends ForecastsFactory<RegArimaDocument> {

        public FCastsFactory() {
            super(RegArimaDocument.class, RegSarimaViews.MODEL_FCASTS, MODELEXTRACTOR);
        }

        @Override
        public int getPosition() {
            return 201000;
        }
    }

    @ServiceProvider(service = IProcDocumentItemFactory.class, position = 200000 + 2000)
    public static class FCastsOutFactory extends OutOfSampleTestFactory<RegArimaDocument> {

        public FCastsOutFactory() {
            super(RegArimaDocument.class, RegSarimaViews.MODEL_FCASTS_OUTOFSAMPLE, MODELEXTRACTOR);
        }

        @Override
        public int getPosition() {
            return 202000;
        }
    }

//</editor-fold>
//
//<editor-fold defaultstate="collapsed" desc="REGISTER MODEL">
    @ServiceProvider(service = IProcDocumentItemFactory.class, position = 300000 + 1000)
    public static class ModelRegsFactory extends ModelRegressorsFactory<RegArimaDocument> {

        public ModelRegsFactory() {
            super(RegArimaDocument.class, RegSarimaViews.MODEL_REGS, MODELEXTRACTOR);
        }

        @Override
        public int getPosition() {
            return 301000;
        }
    }

    @ServiceProvider(service = IProcDocumentItemFactory.class, position = 300000 + 2000)
    public static class ArimaFactory extends ModelArimaFactory {

        public ArimaFactory() {
            super(RegArimaDocument.class, RegSarimaViews.MODEL_ARIMA, MODELEXTRACTOR);
        }

        @Override
        public int getPosition() {
            return 302000;
        }
    }

    @ServiceProvider(service = IProcDocumentItemFactory.class, position = 300000 + 3000)
    public static class PreprocessingDetFactory extends ProcDocumentItemFactory<RegArimaDocument, RegArimaDocument> {

        public PreprocessingDetFactory() {
            super(RegArimaDocument.class, RegSarimaViews.MODEL_DET,
                    source -> source, new GenericTableUI(false,
                            ModellingDictionary.Y_LIN, ModellingDictionary.DET,
                            ModellingDictionary.CAL, ModellingDictionary.TDE, ModellingDictionary.EE,
                            ModellingDictionary.OUT, ModellingDictionary.FULL_RES));
        }

        @Override
        public int getPosition() {
            return 303000;
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="REGISTER RESIDUALS">
    @ServiceProvider(service = IProcDocumentItemFactory.class, position = 400000 + 1000)
    public static class ModelResFactory extends ProcDocumentItemFactory<RegArimaDocument, TsData> {

        public ModelResFactory() {
            super(RegArimaDocument.class, RegSarimaViews.MODEL_RES, RESEXTRACTOR,
                    new ResidualsUI()
                    );
        }

        @Override
        public int getPosition() {
            return 401000;
        }
    }

    @ServiceProvider(service = IProcDocumentItemFactory.class, position = 400000 + 2000)
    public static class ModelResStatsFactory extends NiidTestsFactory<RegArimaDocument> {

        public ModelResStatsFactory() {
            super(RegArimaDocument.class, RegSarimaViews.MODEL_RES_STATS, MODELEXTRACTOR);
        }

        @Override
        public int getPosition() {
            return 402000;
        }
    }

    @ServiceProvider(service = IProcDocumentItemFactory.class, position = 400000 + 3000)
    public static class ModelResDist extends ProcDocumentItemFactory<RegArimaDocument, TsData> {

        public ModelResDist() {
            super(RegArimaDocument.class, RegSarimaViews.MODEL_RES_DIST, RESEXTRACTOR,
                    new ResidualsDistUI());
                   
        }

        @Override
        public int getPosition() {
            return 403000;
        }
    }

    @ServiceProvider(service = IProcDocumentItemFactory.class, position = 400000 + 4000)
    public static class ModelResSpectrum extends ProcDocumentItemFactory<RegArimaDocument, TsData>  {

        public ModelResSpectrum() {
            super(RegArimaDocument.class, RegSarimaViews.MODEL_RES_SPECTRUM, RESEXTRACTOR,
                    new SpectrumUI(true));
        }

        @Override
        public int getPosition() {
            return 404000;
        }
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="REGISTER DETAILS">
    @ServiceProvider(service = IProcDocumentItemFactory.class, position = 500000)
    public static class LFactory extends LikelihoodFactory<RegArimaDocument> {

        public LFactory() {
            super(RegArimaDocument.class, RegSarimaViews.MODEL_LIKELIHOOD, MODELEXTRACTOR);
            setAsync(true);
        }

        @Override
        public int getPosition() {
            return 500000;
        }
    }
//</editor-fold>
    
    
}
