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
import java.io.StringWriter;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.HeaderPortlet;
import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.MutableRenderParameters;
import jakarta.portlet.Portlet;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletMode;
import jakarta.portlet.RenderParameters;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.RenderURL;
import jakarta.portlet.WindowState;
import jakarta.portlet.annotations.PortletApplication;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.PortletQName;
import jakarta.portlet.annotations.PublicRenderParameterDefinition;
import jakarta.portlet.tck.beans.TestLink;
import jakarta.portlet.tck.beans.TestResult;
import jakarta.portlet.tck.util.ModuleTestCaseDetails;

import static jakarta.portlet.tck.util.ModuleTestCaseDetails.V3HEADERPORTLETTESTS_SPEC14_RENDERSTATE_APIHEADER_GETRENDERPARAMETERS;
import static jakarta.portlet.tck.util.ModuleTestCaseDetails.V3HEADERPORTLETTESTS_SPEC14_RENDERSTATE_APIHEADER_GETPORTLETMODE;
import static jakarta.portlet.tck.util.ModuleTestCaseDetails.V3HEADERPORTLETTESTS_SPEC14_RENDERSTATE_APIHEADER_GETWINDOWSTATE;
import static jakarta.portlet.tck.constants.Constants.RESULT_ATTR_PREFIX;

/**
 * This portlet implements several test cases for the JSR 362 TCK. The test case names
 * are defined in the /src/main/resources/xml-resources/additionalTCs.xml
 * file. The build process will integrate the test case names defined in the 
 * additionalTCs.xml file into the complete list of test case names for execution by the driver.
 *
 */
@PortletApplication(publicParams = {
      @PublicRenderParameterDefinition(identifier = "tr3_public", qname = @PortletQName(localPart = "tr3_public", namespaceURI = "")) })
@PortletConfiguration(portletName = "HeaderPortletTests_SPEC14_RenderState_ApiHeader", publicParams = {
"tr3_public" })
public class HeaderPortletTests_SPEC14_RenderState_ApiHeader implements Portlet, HeaderPortlet {

   @Override
   public void init(PortletConfig config) throws PortletException {
   }

   @Override
   public void destroy() {
   }

   @Override
   public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException, IOException {
   }

   @Override
   public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
     
      PrintWriter writer = renderResponse.getWriter();
      String msg = (String) renderRequest.getAttribute(
            RESULT_ATTR_PREFIX + "HeaderPortletTests_SPEC14_RenderState_ApiHeader");
      writer.write("<p>" + msg + "</p>\n");
      renderRequest.removeAttribute(
            RESULT_ATTR_PREFIX + "HeaderPortletTests_SPEC14_RenderState_ApiHeader");

   }

   @Override
   public void renderHeaders(HeaderRequest headerRequest, HeaderResponse headerResponse)
         throws PortletException, IOException {
      StringWriter writer = new StringWriter();
      
      ModuleTestCaseDetails tcd = new ModuleTestCaseDetails();

      /* TestCase: V3HeaderPortletTests_SPEC14_RenderState_ApiHeader_getPortletMode */
      /* Details: "Method getPortletMode(): Returns current PortletMode "           */
      {
         TestResult result = tcd.getTestResultFailed(V3HEADERPORTLETTESTS_SPEC14_RENDERSTATE_APIHEADER_GETPORTLETMODE);
         if(headerRequest.getPortletMode().equals(PortletMode.VIEW)){
            result.setTcSuccess(true);
         }
         result.writeTo(writer);
      }

      /* TestCase: V3HeaderPortletTests_SPEC14_RenderState_ApiHeader_getWindowState */
      /* Details: "Method getWindowState(): Returns current WindowState"            */
      {
         TestResult result = tcd.getTestResultFailed(V3HEADERPORTLETTESTS_SPEC14_RENDERSTATE_APIHEADER_GETWINDOWSTATE);
         if(headerRequest.getWindowState().equals(WindowState.NORMAL)){
            result.setTcSuccess(true);
         }
         result.writeTo(writer);
      }

      /* TestCase: V3HeaderPortletTests_SPEC14_RenderState_ApiHeader_getRenderParameters */
      /* Details: "Method getRenderParameters(): Returns an immutable               */
      /* RenderParameters object representing the private and public render         */
      /* parameters"                                                                */
      RenderParameters renderParams = headerRequest.getRenderParameters();
      if (!renderParams.isEmpty() && renderParams.isPublic("tr3_public")
            && renderParams.getValue("tr3_public") != null
            && renderParams.getValue("tr3_public").equals("true")
            && renderParams.getValue("tr3_private") != null
            && renderParams.getValue("tr3_private").equals("true")) {
         TestResult result = tcd.getTestResultFailed(
               V3HEADERPORTLETTESTS_SPEC14_RENDERSTATE_APIHEADER_GETRENDERPARAMETERS);
         result.setTcSuccess(true);
         result.writeTo(writer);
      } else {
         RenderURL renderURL = headerResponse.createRenderURL();
         MutableRenderParameters mutableRenderParams = renderURL
               .getRenderParameters();
         mutableRenderParams.setValue("tr3_private", "true");
         mutableRenderParams.setValue("tr3_public", "true");
         TestLink tb = new TestLink(
               V3HEADERPORTLETTESTS_SPEC14_RENDERSTATE_APIHEADER_GETRENDERPARAMETERS,
               renderURL);
         tb.writeTo(writer);
      }
      
      headerRequest.setAttribute(
            RESULT_ATTR_PREFIX + "HeaderPortletTests_SPEC14_RenderState_ApiHeader",
            writer.toString());
            
   }

}
