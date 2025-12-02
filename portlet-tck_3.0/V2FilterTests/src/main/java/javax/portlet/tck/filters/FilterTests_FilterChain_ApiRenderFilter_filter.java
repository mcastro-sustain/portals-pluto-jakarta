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

import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.filter.FilterChain;
import jakarta.portlet.filter.FilterConfig;
import jakarta.portlet.filter.RenderFilter;

/**
 * Filter for JSR 362 request dispatcher testing. Used by portlet:
 * FilterTests_FilterChain_ApiRenderFilter
 *
 * @author ahmed
 *
 */
public class FilterTests_FilterChain_ApiRenderFilter_filter implements RenderFilter {

  public static boolean tr0_success = false;

  @SuppressWarnings("unused")
  private FilterConfig filterConfig;

  @Override
  public void init(FilterConfig filterConfig) throws PortletException {
    this.filterConfig = filterConfig;
  }

  @Override
  public void destroy() {}

  @Override
  public void doFilter(RenderRequest portletReq, RenderResponse portletResp, FilterChain chain)
      throws IOException, PortletException {

    /* TestCase: V2FilterTests_FilterChain_ApiRenderFilter_invokeRenderFilter */
    /* Details: "Invoking doFilter(RenderRequest, RenderResponse): causes */
    /* next filter to be invoked" */
    FilterTests_FilterChain_ApiRenderFilter_filter.tr0_success = true;

    chain.doFilter(portletReq, portletResp);
  }
}
