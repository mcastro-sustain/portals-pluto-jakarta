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

import static jakarta.portlet.tck.beans.JSR286ApiTestCaseDetails.V2PORTLETTESTS_EVENT_APIEVENT_GETQNAME1;
import static jakarta.portlet.tck.beans.JSR286ApiTestCaseDetails.V2PORTLETTESTS_EVENT_APIEVENT_GETQNAME2;
import static jakarta.portlet.tck.beans.JSR286ApiTestCaseDetails.V2PORTLETTESTS_EVENT_APIEVENT_GETNAME1;
import static jakarta.portlet.tck.beans.JSR286ApiTestCaseDetails.V2PORTLETTESTS_EVENT_APIEVENT_GETNAME2;
import static jakarta.portlet.tck.beans.JSR286ApiTestCaseDetails.V2PORTLETTESTS_EVENT_APIEVENT_GETVALUE1;
import static jakarta.portlet.tck.beans.JSR286ApiTestCaseDetails.V2PORTLETTESTS_EVENT_APIEVENT_GETVALUE2;
import static jakarta.portlet.tck.beans.JSR286SpecTestCaseDetails.V2ADDLPORTLETTESTS_SPEC2_15_EVENTEVENTHANDLING_EVENTPROCESSEVENT4;
import static jakarta.portlet.tck.beans.JSR286SpecTestCaseDetails.V2ADDLPORTLETTESTS_SPEC2_6_RENDERCONFIG_GETPUBLISHINGEVENTQNAMES3;
import static jakarta.portlet.tck.constants.Constants.BUTTON_PARAM_NAME;
import static jakarta.portlet.tck.constants.Constants.TCKNAMESPACE;
import static jakarta.portlet.tck.constants.Constants.THREADID_ATTR;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.Portlet;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletURL;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.tck.beans.JSR286SpecTestCaseDetails;
import jakarta.portlet.tck.beans.TestButton;
import jakarta.portlet.tck.beans.TestResult;
import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This portlet implements several test cases for the JSR 362 TCK. The test case names are defined
 * in the /src/main/resources/xml-resources/additionalTCs.xml file. The build process will integrate
 * the test case names defined in the additionalTCs.xml file into the complete list of test case
 * names for execution by the driver.
 *
 * This is the main portlet for the test cases. If the test cases call for events, this portlet will
 * initiate the events, but not process them. The processing is done in the companion portlet
 * PortletTests_Event_ApiEvent_event
 *
 */
public class PortletTests_Event_ApiEvent implements Portlet {

  @SuppressWarnings("unused")
  private final Logger LOGGER = LoggerFactory.getLogger(PortletTests_Event_ApiEvent.class);

  private PortletConfig portletConfig = null;


  @Override
  public void init(PortletConfig config) throws PortletException {
    this.portletConfig = config;
  }

  @Override
  public void destroy() {}

  @Override
   public void processAction(ActionRequest portletReq, ActionResponse portletResp) throws PortletException, IOException {

      portletResp.setRenderParameters(portletReq.getParameterMap());
      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

      String val = portletReq.getParameter(BUTTON_PARAM_NAME);
      if (val != null && val.equals(V2PORTLETTESTS_EVENT_APIEVENT_GETVALUE2)) {
         QName eventQName = new QName(TCKNAMESPACE, "PortletTests_Event_ApiEvent2");
         portletResp.setEvent(eventQName, null);
      } else {
         QName eventQName = new QName(TCKNAMESPACE, "PortletTests_Event_ApiEvent");
         portletResp.setEvent(eventQName, "Hi");
         portletResp.setEvent("PortletTests_Event_ApiEvent_localPart", "Hi!");
      }
   }

  @Override
  public void render(RenderRequest portletReq, RenderResponse portletResp)
      throws PortletException, IOException {

    long tid = Thread.currentThread().getId();
    portletReq.setAttribute(THREADID_ATTR, tid);
    JSR286SpecTestCaseDetails tcd_spec = new JSR286SpecTestCaseDetails();

    PrintWriter writer = portletResp.getWriter();

    /* TestCase: V2PortletTests_Event_ApiEvent_getQName1 */
    /* Details: "Method getQName(): Returns the javax.xml.namespace.QName */
    /* object for the Event" */
    {
      PortletURL aurl = portletResp.createActionURL();
      aurl.setParameters(portletReq.getPrivateParameterMap());
      TestButton tb = new TestButton(V2PORTLETTESTS_EVENT_APIEVENT_GETQNAME1, aurl);
      tb.writeTo(writer);
    }

    /* TestCase: V2PortletTests_Event_ApiEvent_getQName2 */
    /* Details: "Method getQName(): Returned value may not be null" */
    {
      PortletURL aurl = portletResp.createActionURL();
      aurl.setParameters(portletReq.getPrivateParameterMap());
      TestButton tb = new TestButton(V2PORTLETTESTS_EVENT_APIEVENT_GETQNAME2, aurl);
      tb.writeTo(writer);
    }

    /* TestCase: V2PortletTests_Event_ApiEvent_getName1 */
    /* Details: "Method getName(): Returns a String containing the event */
    /* local name" */
    {
      PortletURL aurl = portletResp.createActionURL();
      aurl.setParameters(portletReq.getPrivateParameterMap());
      TestButton tb = new TestButton(V2PORTLETTESTS_EVENT_APIEVENT_GETNAME1, aurl);
      tb.writeTo(writer);
    }

    /* TestCase: V2PortletTests_Event_ApiEvent_getName2 */
    /* Details: "Method getName(): Returned value may not be null" */
    {
      PortletURL aurl = portletResp.createActionURL();
      aurl.setParameters(portletReq.getPrivateParameterMap());
      TestButton tb = new TestButton(V2PORTLETTESTS_EVENT_APIEVENT_GETNAME2, aurl);
      tb.writeTo(writer);
    }

    /* TestCase: V2PortletTests_Event_ApiEvent_getValue1 */
    /* Details: "Method getValue(String, String): Returns a */
    /* java.io.Serializable object representing the event payload" */
    {
      PortletURL aurl = portletResp.createActionURL();
      aurl.setParameters(portletReq.getPrivateParameterMap());
      TestButton tb = new TestButton(V2PORTLETTESTS_EVENT_APIEVENT_GETVALUE1, aurl);
      tb.writeTo(writer);
    }

    /* TestCase: V2PortletTests_Event_ApiEvent_getValue2 */
    /* Details: "Method getValue(String, String): Returned value is null */
    /* if the event has no payload" */
    {
      PortletURL aurl = portletResp.createActionURL();
      aurl.setParameters(portletReq.getPrivateParameterMap());
      TestButton tb = new TestButton(V2PORTLETTESTS_EVENT_APIEVENT_GETVALUE2, aurl);
      tb.writeTo(writer);
    }

    /* TestCase: V2AddlPortletTests_SPEC2_15_EventEventHandling_eventProcessEvent4 */
    /* Details: "If an event is published with only the local part of the */
    /* Qname, the namespace must be the default namespace defined in the */
    /* deployment descriptor" */
    {
      PortletURL aurl = portletResp.createActionURL();
      aurl.setParameters(portletReq.getPrivateParameterMap());
      TestButton tb =
          new TestButton(V2ADDLPORTLETTESTS_SPEC2_15_EVENTEVENTHANDLING_EVENTPROCESSEVENT4, aurl);
      tb.writeTo(writer);
    }

    /* TestCase: V2AddlPortletTests_SPEC2_6_RenderConfig_getPublishingEventQnames3 */
    /* Details: "getPublishingEventQnames - If the event was defined */
    /* using the &lt;name&gt; element rather than the &lt;qname&gt; */
    /* element, default namespace defined with the */
    /* &lt;default-namespace&gt; element must be added as the event */
    /* namespace" */
    TestResult tr4 = tcd_spec
        .getTestResultFailed(V2ADDLPORTLETTESTS_SPEC2_6_RENDERCONFIG_GETPUBLISHINGEVENTQNAMES3);
    Enumeration<QName> publishingEvents = portletConfig.getPublishingEventQNames();
    while (publishingEvents.hasMoreElements()) {
      QName qName = publishingEvents.nextElement();
      if (qName.getNamespaceURI().equals("https://www.apache.org")) {
        tr4.setTcSuccess(true);
        break;
      }
    }
    tr4.writeTo(writer);

  }

}
