//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.11.27 at 03:27:52 PM CET 
//


package com.telespazio.hsaf.ordermanagement.orderlist.xsd.autogeneratedClasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegionOfInterest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegionOfInterest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="upperLeftLatitude" type="{}latitude"/>
 *         &lt;element name="upperLeftLongitude" type="{}longitude"/>
 *         &lt;element name="lowerRightLatitude" type="{}latitude"/>
 *         &lt;element name="lowerRightLongitude" type="{}longitude"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegionOfInterest", propOrder = {
    "upperLeftLatitude",
    "upperLeftLongitude",
    "lowerRightLatitude",
    "lowerRightLongitude"
})
public class RegionOfInterest {

    protected float upperLeftLatitude;
    protected float upperLeftLongitude;
    protected float lowerRightLatitude;
    protected float lowerRightLongitude;

    /**
     * Gets the value of the upperLeftLatitude property.
     * 
     */
    public float getUpperLeftLatitude() {
        return upperLeftLatitude;
    }

    /**
     * Sets the value of the upperLeftLatitude property.
     * 
     */
    public void setUpperLeftLatitude(float value) {
        this.upperLeftLatitude = value;
    }

    /**
     * Gets the value of the upperLeftLongitude property.
     * 
     */
    public float getUpperLeftLongitude() {
        return upperLeftLongitude;
    }

    /**
     * Sets the value of the upperLeftLongitude property.
     * 
     */
    public void setUpperLeftLongitude(float value) {
        this.upperLeftLongitude = value;
    }

    /**
     * Gets the value of the lowerRightLatitude property.
     * 
     */
    public float getLowerRightLatitude() {
        return lowerRightLatitude;
    }

    /**
     * Sets the value of the lowerRightLatitude property.
     * 
     */
    public void setLowerRightLatitude(float value) {
        this.lowerRightLatitude = value;
    }

    /**
     * Gets the value of the lowerRightLongitude property.
     * 
     */
    public float getLowerRightLongitude() {
        return lowerRightLongitude;
    }

    /**
     * Sets the value of the lowerRightLongitude property.
     * 
     */
    public void setLowerRightLongitude(float value) {
        this.lowerRightLongitude = value;
    }

}