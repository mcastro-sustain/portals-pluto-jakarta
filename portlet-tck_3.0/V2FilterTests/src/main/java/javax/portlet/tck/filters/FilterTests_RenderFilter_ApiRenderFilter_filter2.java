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

import static jakarta.portlet.tck.beans.JSR286ApiTestCaseDetails.V2FILTERTESTS_RENDERFILTER_APIRENDERFILTER_CANBECONFIGURED2;
import static jakarta.portlet.tck.beans.JSR286ApiTestCaseDetails.V2FILTERTESTS_RENDERFILTER_APIRENDERFILTER_DOFILTERPROCESSRENDER2;

/**
 * Filter for JSR 362 request dispatcher testing. Used by portlet:
 * FilterTests_RenderFilter_ApiRenderFilter
 *
 * @author ahmed
 *
 */
public class FilterTests_RenderFilter_ApiRenderFilter_filter2 implements RenderFilter {

  public static boolean tr2_success = false;
  public static boolean tr3_success = false;


  @Override
  public void init(FilterConfig filterConfig) throws PortletException {}

  @Override
  public void destroy() {}

  @Override
  public void doFilter(RenderRequest portletReq, RenderResponse portletResp, FilterChain chain)
      throws IOException, PortletException {

    PrintWriter writer = portletResp.getWriter();
    JSR286ApiTestCaseDetails tcd = new JSR286ApiTestCaseDetails();

    /* TestCase: V2FilterTests_RenderFilter_ApiRenderFilter_canBeConfigured2 */
    /* Details: "Multiple RenderFilter classes can be configured in the */
    /* portlet descriptor" */
    TestResult tr1 =
        tcd.getTestResultFailed(V2FILTERTESTS_RENDERFILTER_APIRENDERFILTER_CANBECONFIGURED2);
    tr1.setTcSuccess(true);
    tr1.writeTo(writer);

    /* TestCase: V2FilterTests_RenderFilter_ApiRenderFilter_doFilterIsCalled */
    /* Details: "The doFilter(RenderRequest, RenderResponse, */
    /* FilterChain): method is called before the processRender method for */
    /* the portlet" */
    tr2_success = true;

    /* TestCase: V2FilterTests_RenderFilter_ApiRenderFilter_doFilterProcessRender1 */
    /* Details: "After the doFilter(RenderRequest, RenderResponse, */
    /* FilterChain): method has sucessfully completed and invokes the */
    /* next filter, the processRenderMethod is called" */
    tr3_success = true;

    /* TestCase: V2FilterTests_RenderFilter_ApiRenderFilter_doFilterProcessRender2 */
    /* Details: "After the doFilter(RenderRequest, RenderResponse, */
    /* FilterChain): method has sucessfully completed and invokes the */
    /* next filter, the next filter in the chain is called if multiple */
    /* filters are defined" */
    TestResult tr4 =
        tcd.getTestResultFailed(V2FILTERTESTS_RENDERFILTER_APIRENDERFILTER_DOFILTERPROCESSRENDER2);
    if (FilterTests_RenderFilter_ApiRenderFilter_filter.tr4_success) {
      tr4.setTcSuccess(true);
    }
    tr4.writeTo(writer);

    chain.doFilter(portletReq, portletResp);

  }
}
