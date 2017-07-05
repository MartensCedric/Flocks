package com.cedricmartens.flocks;

/**
 * Created by martens on 7/5/17.
 */
public class MathUtils
{
	static public float map (float value, float inRangeStart, float inRangeEnd, float outRangeStart, float outRangeEnd) {
    		return outRangeStart + (outRangeEnd - outRangeStart) * ((value - inRangeStart) / (inRangeEnd - inRangeStart));
    	}


    static public double map (double value, double inRangeStart, double inRangeEnd, double outRangeStart, double outRangeEnd) {
    		return outRangeStart + (outRangeEnd - outRangeStart) * ((value - inRangeStart) / (inRangeEnd - inRangeStart));
    	}
}
