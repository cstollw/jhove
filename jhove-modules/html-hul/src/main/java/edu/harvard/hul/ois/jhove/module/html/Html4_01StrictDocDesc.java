/**********************************************************************
 * Jhove - JSTOR/Harvard Object Validation Environment
 * Copyright 2004 by JSTOR and the President and Fellows of Harvard College
 **********************************************************************/

package edu.harvard.hul.ois.jhove.module.html;

//import edu.harvard.hul.ois.jhove.*;
import java.util.*;
//import java.io.*;

/**
 * This class describes the requirements of an HTML 4.01 Strict document.
 *
 * @author Gary McGath
 *
 */
public class Html4_01StrictDocDesc extends Html4StrictDocDesc {

    /* Static, private map of supported tags. 
     * For efficiency, we create a static Map
     * of supported tags just once, then assign that to stSupportedElements
     * in the constructor. */
    private static Map stSupportedElements;
    
    /* Static initializer.  A superclass is initialized before its
     * subclass, so we can count on the static initializer of HtmlDocDesc
     * to have run already.  
     */
    static {
        stSupportedElements = new HashMap (280);
        classInit4 (stSupportedElements);
        
        int i;
        String name;
        HtmlTagDesc td;
        
        
        addSupElement (stSupportedElements);
        addSubElement (stSupportedElements);
        addSpanElement (stSupportedElements);
        addBdoElement (stSupportedElements);
        addBrElement (stSupportedElements, coreAttrs);

        addBodyElement (stSupportedElements);
        addAddressElement (stSupportedElements);
        addDivElement (stSupportedElements);
        addAElement (stSupportedElements);
        addMapElement (stSupportedElements);
        
        HtmlAttributeDesc shapeAtt = new HtmlAttributeDesc ("shape", 
            new String[] {"rect", "circle", "poly", "default" },
            HtmlAttributeDesc.REQUIRED);
        addAreaElement (stSupportedElements, shapeAtt);
        addLinkElement (stSupportedElements);
        addImgElement (stSupportedElements);
        addObjectElement (stSupportedElements);
        addParamElement (stSupportedElements);
        addHrElement (stSupportedElements);
        addPElement (stSupportedElements);

        /* The heading (H1-H6) elements */
        for (i = 0; i < headings.length; i++) {
            name = headings[i];
            td = new HtmlTagDesc (name, true, true, inlineContent, bigAttrs);
            stSupportedElements.put (name, td);
        }

        addPreElement (stSupportedElements);
        addQElement (stSupportedElements);
        addBlockquoteElement (stSupportedElements);
        addInsElement (stSupportedElements);
        addDelElement (stSupportedElements);
        
        addDlElement (stSupportedElements);
        addDtElement (stSupportedElements);
        addDdElement (stSupportedElements);

        addOlElement (stSupportedElements);
        addUlElement (stSupportedElements);
        addLiElement (stSupportedElements);
        addFormElement (stSupportedElements);
        addLabelElement (stSupportedElements);
        
        addInputElement (stSupportedElements);
        addSelectElement (stSupportedElements);
        addOptgroupElement (stSupportedElements);
        addOptionElement (stSupportedElements);
        addTextareaElement (stSupportedElements);
        addFieldsetElement (stSupportedElements);
        addLegendElement (stSupportedElements);
        addButtonElement (stSupportedElements);
        addTableElement (stSupportedElements);
        
        HtmlAttributeDesc valignAtt = 
            new HtmlAttributeDesc ("valign", 
                new String[] { "top", "middle", "bottom", "baseline" },
                HtmlAttributeDesc.IMPLIED);
        List cellalignAttrs = new ArrayList (4);  // combine cellhalign and cellvalign
        cellalignAttrs.add (new HtmlAttributeDesc ("align",
                new String[] {"left", "center", "right", "justify", "char" },
                HtmlAttributeDesc.IMPLIED));
        addSimpleAttribute (cellalignAttrs, "char");
        addSimpleAttribute (cellalignAttrs, "charoff");
        addTheadElement (stSupportedElements, cellalignAttrs);
        addTfootElement (stSupportedElements, cellalignAttrs);
        addTbodyElement (stSupportedElements, cellalignAttrs);
        addTrElement (stSupportedElements);

        addThElement (stSupportedElements);
        addTdElement (stSupportedElements); 
        addCaptionElement 
            (stSupportedElements, inlineContent, valignAtt);
        
        addColgroupElement (stSupportedElements, cellalignAttrs);
        addColElement (stSupportedElements, cellalignAttrs);
        
        addHeadElement (stSupportedElements);
        addTitleElement (stSupportedElements);
        addBaseElement (stSupportedElements);
        addMetaElement (stSupportedElements);
        addScriptElement (stSupportedElements);
        addNoscriptElement (stSupportedElements);
        addStyleElement (stSupportedElements);
         
        /* The HTML element */
        name = "html";
        List htmlContent = new ArrayList (2);
        htmlContent.add ("head");
        htmlContent.add ("body");
        td = new HtmlTagDesc (name, false, false, htmlContent, i18nAttrs);
        stSupportedElements.put (name, td);
    } 

    /**
     *  Constructor.
     */
    public Html4_01StrictDocDesc ()
    {
        // publish stSupportedElements to superclass
        supportedElements = stSupportedElements;
        init ();
    }
    

    
    private static void addFormElement (Map stSupportedElements)
    {
        final String name = "form";
        List atts = new ArrayList (bigAttrs.size () + 8);
        atts.addAll (bigAttrs);
        addSimpleAttribute (atts, "action");
        addSimpleAttribute (atts, "method");
        addSimpleAttribute (atts, "enctype");
        addSimpleAttribute (atts, "accept");     // 4.01
        addSimpleAttribute (atts, "name");       // 4.01
        addSimpleAttribute (atts, "onsubmit");
        addSimpleAttribute (atts, "onreset");
        addSimpleAttribute (atts, "accept-charset");
        List formContent = new ArrayList (blockContent.size ());
        formContent.addAll (blockContent);
        formContent.add ("script");
        removeStringsFromList (formContent, new String[] { "form" });
        HtmlTagDesc td = new HtmlTagDesc (name, true, true, formContent, atts);
        stSupportedElements.put (name, td);
    }

    private static void addHrElement (Map stSupportedElements)
    {
        String name = "hr";        
        HtmlTagDesc td = new HtmlTagDesc (name, true, false, null, bigAttrs);
        stSupportedElements.put (name, td);
    }

    private static void addImgElement (Map stSupportedElements)
    {
        String name = "img";
        List atts = new ArrayList (bigAttrs.size () + 10);
        atts.addAll (bigAttrs);
        addRequiredAttribute (atts, "src");
        addRequiredAttribute (atts, "alt");
        addSimpleAttribute (atts, "longdesc");
        addSimpleAttribute (atts, "name");  // new to 4.01
        addSimpleAttribute (atts, "height");
        addSimpleAttribute (atts, "width");
        addSimpleAttribute (atts, "usemap");
        addSelfAttribute (atts, "ismap");
        HtmlTagDesc td = new HtmlTagDesc (name, true, false, null, atts);
        stSupportedElements.put (name, td);
    }

    private static void addInputElement 
        (Map stSupportedElements)
    {
        final String name = "input";
        List atts = new ArrayList (biggerAttrs.size () + 20);
        atts.addAll (biggerAttrs);
        atts.add (new HtmlAttributeDesc ("type", 
            new String[] {"text", "password", "checkbox", "radio", "submit", 
                        "reset", "file", "hidden", "image", "button"},
            HtmlAttributeDesc.OTHER));
        addSimpleAttribute (atts, "name");
        addSimpleAttribute (atts, "value");
        addSelfAttribute (atts, "checked");
        addSelfAttribute (atts, "disabled");
        addSelfAttribute (atts, "readonly");
        addSimpleAttribute (atts, "size");
        addSimpleAttribute (atts, "maxlength");
        addSimpleAttribute (atts, "src");
        addSimpleAttribute (atts, "alt");
        addSimpleAttribute (atts, "usemap");
        addSelfAttribute (atts, "ismap");    // 4.01
        addSimpleAttribute (atts, "tabindex");
        addSimpleAttribute (atts, "accesskey");
        addSimpleAttribute (atts, "onfocus");
        addSimpleAttribute (atts, "onblur");
        addSimpleAttribute (atts, "onselect");
        addSimpleAttribute (atts, "onchange");
        addSimpleAttribute (atts, "accept");
        HtmlTagDesc td = new HtmlTagDesc (name, true, true, null, atts);
        stSupportedElements.put (name, td);
    }
}
