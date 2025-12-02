/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package jakarta.portlet.tck.portlets;

import static jakarta.portlet.tck.beans.JSR286SpecTestCaseDetails.V2ADDLENVIRONMENTTESTS_SPEC2_17_RENDERPREFERENCES_DEPLOYMENT1;
import static jakarta.portlet.tck.beans.JSR286SpecTestCaseDetails.V2ADDLENVIRONMENTTESTS_SPEC2_17_RENDERPREFERENCES_DEPLOYMENT2;
import static jakarta.portlet.tck.constants.Constants.THREADID_ATTR;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.Portlet;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletPreferences;
import jakarta.portlet.ReadOnlyException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.ResourceServingPortlet;
import jakarta.portlet.tck.beans.JSR286SpecTestCaseDetails;
import jakarta.portlet.tck.beans.TestResult;

/**
 * This portlet implements several test cases for the JSR 362 TCK. The test case names are defined
 * in the /src/main/resources/xml-resources/additionalTCs.xml file. The build process will integrate
 * the test case names defined in the additionalTCs.xml file into the complete list of test case
 * names for execution by the driver.
 *
 * This is the main portlet for the test cases. If the test cases call for events, this portlet will
 * initiate the events, but not process them. The processing is done in the companion portlet
 * AddlEnvironmentTests_SPEC2_17_RenderPreferences_event
 *
 */
@SuppressWarnings("deprecation")
public class AddlEnvironmentTests_SPEC2_17_RenderPreferences
    implements Portlet, ResourceServingPortlet {

  @Override
  public void init(PortletConfig config) throws PortletException {
  }

  @Override
  public void destroy() {}

  @Override
  public void processAction(ActionRequest portletReq, ActionResponse portletResp)
      throws PortletException, IOException {

    portletResp.setRenderParameters(portletReq.getParameterMap());
    long tid = Thread.currentThread().getId();
    portletReq.setAttribute(THREADID_ATTR, tid);

  }

  @Override
  public void serveResource(ResourceRequest portletReq, ResourceResponse portletResp)
      throws PortletException, IOException {

    long tid = Thread.currentThread().getId();
    portletReq.setAttribute(THREADID_ATTR, tid);

  }

  @Override
  public void render(RenderRequest portletReq, RenderResponse portletResp)
      throws PortletException, IOException {

    long tid = Thread.currentThread().getId();
    portletReq.setAttribute(THREADID_ATTR, tid);

    PrintWriter writer = portletResp.getWriter();

    JSR286SpecTestCaseDetails tcd = new JSR286SpecTestCaseDetails();

    PortletPreferences portletPrefs = portletReq.getPreferences();

    // Create result objects for the tests

    /* TestCase: V2AddlEnvironmentTests_SPEC2_17_RenderPreferences_deployment1 */
    /* Details: "The portlet definition may define the preference */
    /* attributes a portlet uses" */
    TestResult tr0 =
        tcd.getTestResultFailed(V2ADDLENVIRONMENTTESTS_SPEC2_17_RENDERPREFERENCES_DEPLOYMENT1);
    try {
      portletPrefs.setValue("tr0", "true");
      tr0.setTcSuccess(true);
    } catch (ReadOnlyException e) {
      tr0.appendTcDetail(e.toString());
    }
    tr0.writeTo(writer);

    /* TestCase: V2AddlEnvironmentTests_SPEC2_17_RenderPreferences_deployment2 */
    /* Details: "A preference attribute definition may include initial */
    /* default values" */
    TestResult tr1 =
        tcd.getTestResultFailed(V2ADDLENVIRONMENTTESTS_SPEC2_17_RENDERPREFERENCES_DEPLOYMENT2);
    if (portletPrefs.getValue("tr1", null).equals("true")) {
      tr1.setTcSuccess(true);
    } else {
      tr1.appendTcDetail(
          "Failed because tr1 default value is not true but " + portletPrefs.getValue("tr1", null));
    }
    tr1.writeTo(writer);

  }

}
