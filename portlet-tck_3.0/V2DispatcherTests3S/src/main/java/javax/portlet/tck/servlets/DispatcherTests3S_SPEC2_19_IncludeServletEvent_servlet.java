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

package jakarta.portlet.tck.servlets;

import static jakarta.portlet.tck.constants.Constants.THREADID_ATTR;

import java.io.IOException;

import jakarta.portlet.PortletRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet for JSR 362 request dispatcher testing. Used by portlet: DispatcherTests3S_SPEC2_19_IncludeServletEvent
 * 
 * @author nick
 * 
 */
public class DispatcherTests3S_SPEC2_19_IncludeServletEvent_servlet extends HttpServlet {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      processTCKReq(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      processTCKReq(req, resp);
   }

   // The tck uses only get & post requests
   protected void processTCKReq(HttpServletRequest request, HttpServletResponse response) throws ServletException,
         IOException {

      PortletRequest portletReq = (PortletRequest) request.getAttribute("jakarta.portlet.request");
      request.getAttribute("jakarta.portlet.response");
      request.getAttribute("jakarta.portlet.config");
      Thread.currentThread().getId();
      portletReq.getAttribute(THREADID_ATTR);

      // Create result objects for the tests

   }
}
