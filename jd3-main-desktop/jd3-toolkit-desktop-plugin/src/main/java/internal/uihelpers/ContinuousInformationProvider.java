/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package internal.uihelpers;

/**
 *
 * @author Jean Palate
 */
public interface ContinuousInformationProvider {

    String getInformation();
    String[] getComponents();
   
    ContinuousDisplayDomain getContinuousDisplayDomain(int npoints);
    ContinuousDisplayDomain getContinuousDisplayDomain(double lower, double upper, int npoints);

    double[] getDataArray(int cmp, ContinuousDisplayDomain domain);
    double getData(int cmp, double x);

    boolean isDefined(int idx);

}
