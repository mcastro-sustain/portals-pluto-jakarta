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

import static jakarta.portlet.tck.util.ModuleTestCaseDetails.V3RESOURCEASYNCTESTS_SPEC_21_ASYNC_CDI2;

import java.io.IOException;

import jakarta.inject.Inject;
import jakarta.portlet.PortletAsyncEvent;
import jakarta.portlet.PortletAsyncListener;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.annotations.PortletRequestScoped;
import jakarta.portlet.tck.beans.TestResult;
import jakarta.portlet.tck.util.ModuleTestCaseDetails;

/**
 * Portlet async listener for TCK tests
 * 
 * @author Scott Nicklous
 */
@PortletRequestScoped
public class AsyncBeanListener implements PortletAsyncListener {
   
   private String testcase;
   
   private ModuleTestCaseDetails tcd = new ModuleTestCaseDetails();

   @Inject private AsyncBean bean;

   
   public void setTestcase(String tc) {
      testcase = tc;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletAsyncListener#onComplete(jakarta.portlet.PortletAsyncEvent)
    */
   @Override
   public void onComplete(PortletAsyncEvent evt) throws IOException {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletAsyncListener#onError(jakarta.portlet.PortletAsyncEvent)
    */
   @Override
   public void onError(PortletAsyncEvent evt) throws IOException {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletAsyncListener#onStartAsync(jakarta.portlet.PortletAsyncEvent)
    */
   @Override
   public void onStartAsync(PortletAsyncEvent evt) throws IOException {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletAsyncListener#onTimeout(jakarta.portlet.PortletAsyncEvent)
    */
   @Override
   public void onTimeout(PortletAsyncEvent evt) throws IOException {

         /* TestCase: V3ResourceAsyncTests_SPEC_21_Async_cdi2                          */
         /* Details: "When CDI is available and the asynchronous thread is started     */
         /* through the PortletAsyncContext object, the PortletAsyncListener runs in   */
         /* the same context as the original request"                                  */
      if (testcase.equals(V3RESOURCEASYNCTESTS_SPEC_21_ASYNC_CDI2)) {
         TestResult result = tcd.getTestResultFailed(testcase);
         assert bean != null;       // otherwise TC logic is broken
         String btc = bean.getTestcase();
         if (btc != null && btc.equals(testcase)) {
            result.setTcSuccess(true);
         } else {
            StringBuilder txt = new StringBuilder(128);
            txt.append("value from injected bean not as expected. ");
            txt.append("Expected: ").append(testcase);
            txt.append(", Actual: ").append(btc);
            result.appendTcDetail(txt.toString());
         }
         ResourceResponse resp = evt.getPortletAsyncContext().getResourceResponse();
         resp.getWriter().write(result.toString());

      }
   }

}
