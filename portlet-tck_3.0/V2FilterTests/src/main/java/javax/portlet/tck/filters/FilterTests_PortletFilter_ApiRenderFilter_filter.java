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


package jakarta.portlet.tck.filters;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.filter.FilterChain;
import jakarta.portlet.filter.FilterConfig;
import jakarta.portlet.filter.RenderFilter;
import jakarta.portlet.tck.beans.JSR286ApiTestCaseDetails;
import jakarta.portlet.tck.beans.TestResult;

import static jakarta.portlet.tck.beans.JSR286ApiTestCaseDetails.V2FILTERTESTS_PORTLETFILTER_APIRENDERFILTER_INITRENDER1;
import static jakarta.portlet.tck.beans.JSR286ApiTestCaseDetails.V2FILTERTESTS_PORTLETFILTER_APIRENDERFILTER_INITRENDER2;

/**
 * Filter for JSR 362 request dispatcher testing. Used by portlet:
 * FilterTests_PortletFilter_ApiRenderFilter
 *
 * @author nick
 *
 */
public class FilterTests_PortletFilter_ApiRenderFilter_filter implements RenderFilter {

  private FilterConfig filterConfig = null;
  private boolean initCalled = false;

  @Override
  public void init(FilterConfig filterConfig) throws PortletException {
    this.filterConfig = filterConfig;
    initCalled = true;
  }

  @Override
  public void destroy() {}

  @Override
  public void doFilter(RenderRequest portletReq, RenderResponse portletResp, FilterChain chain)
      throws IOException, PortletException {

    PrintWriter writer = portletResp.getWriter();

    JSR286ApiTestCaseDetails tcd = new JSR286ApiTestCaseDetails();

    // Create result objects for the tests

    /* TestCase: V2FilterTests_PortletFilter_ApiRenderFilter_initRender1 */
    /* Details: "The init(FilterConfig): method is called when an */
    /* RenderFilter is configured" */
    TestResult tr0 =
        tcd.getTestResultFailed(V2FILTERTESTS_PORTLETFILTER_APIRENDERFILTER_INITRENDER1);
    if (initCalled == true) {
      tr0.setTcSuccess(true);
    }
    tr0.writeTo(writer);

    /* TestCase: V2FilterTests_PortletFilter_ApiRenderFilter_initRender2 */
    /* Details: "The init(FilterConfig): method for an RenderFilter is */
    /* passed a FilterConfig object" */
    TestResult tr1 =
        tcd.getTestResultFailed(V2FILTERTESTS_PORTLETFILTER_APIRENDERFILTER_INITRENDER2);
    if (this.filterConfig != null) {
      tr1.setTcSuccess(true);
    }
    tr1.writeTo(writer);

    chain.doFilter(portletReq, portletResp);
  }
}
