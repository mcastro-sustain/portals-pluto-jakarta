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
import java.util.Map;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.HeaderPortlet;
import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.Portlet;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.RuntimeOption;
import jakarta.portlet.tck.beans.TestResult;
import jakarta.portlet.tck.util.ModuleTestCaseDetails;

import static jakarta.portlet.tck.util.ModuleTestCaseDetails.V3HEADERPORTLETTESTS_SPEC3_6_4_HEADERPORTLET_RENDERHEADERS;
import static jakarta.portlet.tck.util.ModuleTestCaseDetails.V3HEADERPORTLETTESTS_SPEC3_6_4_HEADERPORTLET_RENDERHEADERS2;
import static jakarta.portlet.tck.constants.Constants.RESULT_ATTR_PREFIX;

/**
 * This portlet implements several test cases for the JSR 362 TCK. The test case
 * names are defined in the /src/main/resources/xml-resources/additionalTCs.xml
 * file. The build process will integrate the test case names defined in the
 * additionalTCs.xml file into the complete list of test case names for
 * execution by the driver.
 *
 */

@PortletConfiguration(portletName = "HeaderPortletTests_SPEC3_6_4_HeaderPortlet",
   runtimeOptions = {@RuntimeOption(name = "jakarta.portlet.renderHeaders", values = { "true" })}
)
public class HeaderPortletTests_SPEC3_6_4_HeaderPortlet
      implements Portlet, HeaderPortlet {
   
   private boolean       tr0_success   = false;
   private PortletConfig portletConfig = null;

   @Override
   public void init(PortletConfig config) throws PortletException {
      this.portletConfig = config;
   }

   @Override
   public void destroy() {
   }

   @Override
   public void processAction(ActionRequest actionRequest,
         ActionResponse actionResponse) throws PortletException, IOException {
   }

   @Override
   public void render(RenderRequest renderRequest, RenderResponse renderResponse)
         throws PortletException, IOException {

      PrintWriter writer = renderResponse.getWriter();
      ModuleTestCaseDetails tcd = new ModuleTestCaseDetails();

      /* TestCase: V3HeaderPortletTests_SPEC3_6_4_HeaderPortlet_renderHeaders */
      /*
       * Details: "renderHeaders() method is called before render() method if
       * the portlet implements HeaderPortlet interface."
       */
      {
         TestResult result = tcd.getTestResultFailed(
               V3HEADERPORTLETTESTS_SPEC3_6_4_HEADERPORTLET_RENDERHEADERS);
         result.setTcSuccess(tr0_success);
         result.writeTo(writer);
         tr0_success = false;
      }

      String msg = (String) renderRequest.getAttribute(
            RESULT_ATTR_PREFIX + "HeaderPortletTests_SPEC3_6_4_HeaderPortlet");
      writer.write("<p>" + msg + "</p>\n");
      renderRequest.removeAttribute(
            RESULT_ATTR_PREFIX + "HeaderPortletTests_SPEC3_6_4_HeaderPortlet");

   }

   @Override
   public void renderHeaders(HeaderRequest headerRequest,
         HeaderResponse headerResponse) throws PortletException, IOException {

      StringWriter writer = new StringWriter();

      ModuleTestCaseDetails tcd = new ModuleTestCaseDetails();

      tr0_success = true;

      /*
       * TestCase: V3HeaderPortletTests_SPEC3_6_4_HeaderPortlet_renderHeaders2
       */
      /*
       * Details: "The container runtime option jakarta.portlet.renderHeaders is
       * disregarded for version 3.0 or later."
       */
      {
         TestResult result = tcd.getTestResultFailed(
               V3HEADERPORTLETTESTS_SPEC3_6_4_HEADERPORTLET_RENDERHEADERS2);
         Map<String, String[]> runtimeOptions = portletConfig
               .getContainerRuntimeOptions();
         String[] renderHeaders = runtimeOptions
               .get("jakarta.portlet.renderHeaders");
         if (renderHeaders == null || renderHeaders.length == 0) {
            result.setTcSuccess(true);
         } else {
            result.appendTcDetail(
                  "Failed because jakarta.portlet.renderHeaders is found equal to "
                        + renderHeaders[0]);
         }
         result.writeTo(writer);
      }

      headerRequest.setAttribute(
            RESULT_ATTR_PREFIX + "HeaderPortletTests_SPEC3_6_4_HeaderPortlet",
            writer.toString());
   }

}
