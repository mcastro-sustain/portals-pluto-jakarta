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
import java.util.Map;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.Portlet;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.tck.beans.TestResult;
import jakarta.portlet.tck.util.ModuleTestCaseDetails;

import static jakarta.portlet.tck.util.ModuleTestCaseDetails.V3ANNOTATIONPORTLETAPPCONFIGOVERRIDETESTS_SPEC1_28_PORTLETCONTAINERRUNTIMEOPTIONS_DECLARINGPCRO2;
import static jakarta.portlet.tck.util.ModuleTestCaseDetails.V3ANNOTATIONPORTLETAPPCONFIGOVERRIDETESTS_SPEC1_28_PORTLETCONTAINERRUNTIMEOPTIONS_DECLARINGPCRO3;

public abstract class AnnotationPortletAppConfigOverrideTests_SPEC1_28_PortletContainerRuntimeOptionsBase implements Portlet {
   
   private PortletConfig portletConfig = null;

   @Override
   public void init(PortletConfig config) throws PortletException {
      this.portletConfig = config;
   }

   @Override
   public void destroy() {
   }

   @Override
   public void processAction(ActionRequest portletReq, ActionResponse portletResp) throws PortletException, IOException {
   }

   @Override
   public void render(RenderRequest portletReq, RenderResponse portletResp) throws PortletException, IOException {

      PrintWriter writer = portletResp.getWriter();
      ModuleTestCaseDetails tcd = new ModuleTestCaseDetails();

      /* TestCase: V3AnnotationPortletAppConfigOverrideTests_SPEC1_28_PortletContainerRuntimeOptions_declaringPCRO2 */
      /* Details: "A portlet container runtime option declared in the               */
      /* @PortletApplication annotation could be overridden by deployment           */
      /* descriptor portlet.xml"                                                    */
      {
         TestResult result = tcd.getTestResultFailed(V3ANNOTATIONPORTLETAPPCONFIGOVERRIDETESTS_SPEC1_28_PORTLETCONTAINERRUNTIMEOPTIONS_DECLARINGPCRO2);
         Map<String, String[]> runtimeOptions = portletConfig.getContainerRuntimeOptions();
         if(runtimeOptions.containsKey("jakarta.portlet.escapeXml")
               && runtimeOptions.get("jakarta.portlet.escapeXml")[0].equals("true")){
            result.setTcSuccess(true);
         }
         result.writeTo(writer);
      }

      /* TestCase: V3AnnotationPortletAppConfigOverrideTests_SPEC1_28_PortletContainerRuntimeOptions_declaringPCRO3 */
      /* Details: "The portlet container merges the portlet container runtime       */
      /* options declared in the @PortletApplication annotation and deployment      */
      /* descriptor portlet.xml"                                                    */
      {
         TestResult result = tcd.getTestResultFailed(V3ANNOTATIONPORTLETAPPCONFIGOVERRIDETESTS_SPEC1_28_PORTLETCONTAINERRUNTIMEOPTIONS_DECLARINGPCRO3);
         Map<String, String[]> runtimeOptions = portletConfig.getContainerRuntimeOptions();
         if (isRuntimeOptionsCorrect(runtimeOptions)) {
            result.setTcSuccess(true);
         }
         result.writeTo(writer);
      }

   }

   public abstract boolean isRuntimeOptionsCorrect(Map<String, String[]> runtimeOptions);
}
