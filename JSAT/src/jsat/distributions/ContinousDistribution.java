
package jsat.distributions;

import java.util.Random;
import jsat.linear.Vec;

/**
 *
 * @author Edward Raff
 */
public abstract class ContinousDistribution
{
    abstract public double pdf(double x);
    abstract public double cdf(double x);
    abstract public double invCdf(double p);

    /**
     * The minimum value for which the {@link #pdf(double) } is meant to return a value. Note that {@link Double#NEGATIVE_INFINITY} is a valid return value.
     * @return the minimum value for which the {@link #pdf(double) } is meant to return a value.
     */
    abstract public double min();

    /**
     * The maximum value for which the {@link #pdf(double) } is meant to return a value. Note that {@link Double#POSITIVE_INFINITY} is a valid return value.
     * @return the maximum value for which the {@link #pdf(double) } is meant to return a value.
     */
    abstract public double max();

    public String getDescriptiveName()
    {
        StringBuilder sb = new StringBuilder(getDistributionName());
        sb.append("(");
        String[] vars = getVariables();
        double[] vals = getCurrentVariableValues();
        
        sb.append(vars[0]).append(" = ").append(vals[0]);
        
        for(int i  = 1; i < vars.length; i++)
            sb.append(", ").append(vars[i]).append(" = ").append(vals[i]);
        
        sb.append(")");
        
        return sb.toString();
    }

    abstract public String getDistributionName();

    public double[] generateData(Random rnd, int count)
    {
        double[] data = new double[count];
        for(int i =0; i < count; i++)
            data[i] = invCdf(rnd.nextDouble());

        return data;
    }

    /**
     *
     * @return a string of the variable names this distribution uses
     */
    abstract public String[] getVariables();

    /**
     * @return the current values of the parameters used by this distribution, in the same order as their names are returned by {@link #getVariables() }
     */
    abstract public double[] getCurrentVariableValues();

    abstract public void setVariable(String var, double value);
    
    abstract public ContinousDistribution copy();

    /**
     * Attempts to set the variables used by this distribution based on population sample data, assuming the sample data is from this type of distribution.
     * @param data the data to use to attempt to fit against
     */
    abstract public void setUsingData(Vec data);
    
    
    abstract public double mean();
    abstract public double median();
    abstract public double mode();
    /**
     * Computes the variance of the distribution. Not all distributions have a 
     * finite variance for all parameter values. {@link Double#NaN NaN} may be 
     * returned if the variance is not defined for the current values of the distribution. 
     * {@link Double#POSITIVE_INFINITY Infinity} is a possible value to be returned
     * by some distributions. 
     * 
     * @return the variance of the distribution. 
     */
    abstract public double variance();
    /**
     * Computes the skewness of the distribution. Not all distributions have a 
     * finite skewness for all parameter values. {@link Double#NaN NaN} may be 
     * returned if the skewness is not defined for the current values of the distribution.
     * 
     * @return the skewness of the distribution. 
     */
    abstract public double skewness();
    public double standardDeviation()
    {
        return Math.sqrt(variance());
    }
    

    @Override
    public String toString()
    {
        return getDistributionName();
    }


}
