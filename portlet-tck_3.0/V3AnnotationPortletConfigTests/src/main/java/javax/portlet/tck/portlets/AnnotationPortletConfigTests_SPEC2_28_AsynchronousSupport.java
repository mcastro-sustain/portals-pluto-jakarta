/*  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package jakarta.portlet.tck.portlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.Portlet;
import jakarta.portlet.PortletAsyncContext;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.ResourceServingPortlet;
import jakarta.portlet.ResourceURL;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.tck.beans.TestResult;
import jakarta.portlet.tck.util.ModuleTestCaseDetails;

import static jakarta.portlet.ResourceURL.PAGE;
import static jakarta.portlet.tck.util.ModuleTestCaseDetails.V3ANNOTATIONPORTLETCONFIGTESTS_SPEC2_28_ASYNCHRONOUSSUPPORT_DECLARINGASYNCSUPPORT1;

/**
 * This portlet implements several test cases for the JSR 362 TCK. The test case names
 * are defined in the /src/main/resources/xml-resources/additionalTCs.xml
 * file. The build process will integrate the test case names defined in the 
 * additionalTCs.xml file into the complete list of test case names for execution by the driver.
 *
 */

@PortletConfiguration(
   portletName = "AnnotationPortletConfigTests_SPEC2_28_AsynchronousSupport",
   asyncSupported = false
)
public class AnnotationPortletConfigTests_SPEC2_28_AsynchronousSupport implements Portlet, ResourceServingPortlet {

   @Override
   public void init(PortletConfig config) throws PortletException {}

   @Override
   public void destroy() {}

   @Override
   public void processAction(ActionRequest portletReq, ActionResponse portletResp) throws PortletException, IOException {}

   @Override
   public void render(RenderRequest portletReq, RenderResponse portletResp) throws PortletException, IOException {

      PrintWriter writer = portletResp.getWriter();    
      
      writer.write(
            "<div id=\"AnnotationPortletConfigTests_SPEC2_28_AsynchronousSupport\">no resource output.</div>\n");
      ResourceURL resurl = portletResp.createResourceURL();
      resurl.setCacheability(PAGE);
      writer.write("<script>\n");
      writer.write("(function () {\n");
      writer.write("   var xhr = new XMLHttpRequest();\n");
      writer.write("   xhr.onreadystatechange=function() {\n");
      writer.write("      if (xhr.readyState==4 && xhr.status==200) {\n");
      writer.write(
            "         document.getElementById(\"AnnotationPortletConfigTests_SPEC2_28_AsynchronousSupport\").innerHTML=xhr.responseText;\n");
      writer.write("      }\n");
      writer.write("   };\n");
      writer.write(
            "   xhr.open(\"GET\",\"" + resurl.toString() + "\",true);\n");
      writer.write("   xhr.send();\n");
      writer.write("})();\n");
      writer.write("</script>\n");

   }

   @Override
   public void serveResource(ResourceRequest portletReq, ResourceResponse portletResp)
         throws PortletException, IOException {
      
      ModuleTestCaseDetails tcd = new ModuleTestCaseDetails();
      PrintWriter writer = portletResp.getWriter();
      
      /* TestCase: V3AnnotationPortletConfigTests_SPEC2_28_AsynchronousSupport_declaringAsyncSupport1 */
      /* Details: "Support for asynchronous resource request can be configured      */
      /* using \"asyncSupported\" attribute of @PortletConfiguration annotation."   */
      {
         TestResult result = tcd.getTestResultFailed(V3ANNOTATIONPORTLETCONFIGTESTS_SPEC2_28_ASYNCHRONOUSSUPPORT_DECLARINGASYNCSUPPORT1);
         try{
            PortletAsyncContext ctx = portletReq.startPortletAsync();
            result.appendTcDetail("Failed because async is supported even when it is disabled in @PortletConfiguration annotation");
            result.writeTo(writer);
            ctx.complete();
         } catch (Exception e){
            result.setTcSuccess(true);
            result.appendTcDetail("Passed as async support is disabled, thats why using startPortletAsync() throws exception - "+e.toString());
            result.writeTo(writer);
         }
      }    
   }

}
