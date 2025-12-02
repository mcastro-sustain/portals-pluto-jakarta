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

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.ActionURL;
import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.MutableRenderParameters;
import jakarta.portlet.Portlet;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderParameters;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.annotations.ActionMethod;
import jakarta.portlet.annotations.CustomPortletMode;
import jakarta.portlet.annotations.CustomWindowState;
import jakarta.portlet.annotations.EventDefinition;
import jakarta.portlet.annotations.EventMethod;
import jakarta.portlet.annotations.PortletApplication;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.PortletQName;
import jakarta.portlet.annotations.PublicRenderParameterDefinition;
import jakarta.portlet.annotations.RuntimeOption;
import jakarta.portlet.tck.beans.TestButton;
import jakarta.portlet.tck.beans.TestResult;
import jakarta.portlet.tck.util.ModuleTestCaseDetails;
import javax.xml.namespace.QName;

import static jakarta.portlet.tck.util.ModuleTestCaseDetails.V3ANNOTATIONPORTLETAPPLICATIONCONFIGTESTS_SPEC1_28_EVENTCONFIGURATION_DECLARINGEVENTS1;
import static jakarta.portlet.tck.util.ModuleTestCaseDetails.V3ANNOTATIONPORTLETAPPLICATIONCONFIGTESTS_SPEC1_28_EVENTCONFIGURATION_DECLARINGEVENTS2;

/**
 * This portlet implements several test cases for the JSR 362 TCK. The test case names
 * are defined in the /src/main/resources/xml-resources/additionalTCs.xml
 * file. The build process will integrate the test case names defined in the 
 * additionalTCs.xml file into the complete list of test case names for execution by the driver.
 *
 */
@PortletApplication(
   runtimeOptions = {
      @RuntimeOption(name = "jakarta.portlet.escapeXml", values = { "true" }),
      @RuntimeOption(name = "jakarta.portlet.actionScopedRequestAttributes", values = { "true" })
   },
   customPortletModes={
      @CustomPortletMode(
         name = "custom1",
         portalManaged=false
      ),
      @CustomPortletMode(
         name = "custom2",
         portalManaged=true
      )
   },
   customWindowStates = {
      @CustomWindowState(name = "custom1")   
   },
   publicParams = {
      @PublicRenderParameterDefinition(
         identifier = "tr0_public", 
         qname = @PortletQName(
            localPart = "tr0_public", 
            namespaceURI = ""
         )
      ) 
   },
   events = {
      @EventDefinition(
         qname = @PortletQName(
         localPart = "AnnotationPortletApplicationConfigTests_SPEC1_28_EventConfigurationTr0", 
         namespaceURI = "http://www.apache.org/portals/pluto/portlet-tck_3.0"), 
      payloadType = java.lang.String.class
      ), @EventDefinition(
         qname = @PortletQName(
         localPart = "AnnotationPortletApplicationConfigTests_SPEC1_28_EventConfigurationTr1", 
         namespaceURI = ""), 
      payloadType = java.lang.String.class
      )
   }
)
@PortletConfiguration(portletName = "AnnotationPortletApplicationConfigTests_SPEC1_28_EventConfiguration")
public class AnnotationPortletApplicationConfigTests_SPEC1_28_EventConfiguration implements Portlet {

   @Override
   public void init(PortletConfig config) throws PortletException {}

   @Override
   public void destroy() {}

   @ActionMethod(
      portletName = "AnnotationPortletApplicationConfigTests_SPEC1_28_EventConfiguration", 
      publishingEvents = {
            @PortletQName(
            localPart = "AnnotationPortletApplicationConfigTests_SPEC1_28_EventConfigurationTr0", 
            namespaceURI = "http://www.apache.org/portals/pluto/portlet-tck_3.0"
         ),
         @PortletQName(
            localPart = "AnnotationPortletApplicationConfigTests_SPEC1_28_EventConfigurationTr1", 
            namespaceURI = ""
         )
      }
   )
   public void processAction(ActionRequest portletReq, ActionResponse portletResp) throws PortletException, IOException {
      String action = portletReq.getActionParameters().getValue("inputval");
      if(action!=null){
         if(action.equals(V3ANNOTATIONPORTLETAPPLICATIONCONFIGTESTS_SPEC1_28_EVENTCONFIGURATION_DECLARINGEVENTS1)){
            QName eventQName = new QName(
                  "http://www.apache.org/portals/pluto/portlet-tck_3.0",
                  "AnnotationPortletApplicationConfigTests_SPEC1_28_EventConfigurationTr0");
            portletResp.setEvent(eventQName, "Hi!");
         } else if (action.equals(V3ANNOTATIONPORTLETAPPLICATIONCONFIGTESTS_SPEC1_28_EVENTCONFIGURATION_DECLARINGEVENTS2)){
            portletResp.setEvent("AnnotationPortletApplicationConfigTests_SPEC1_28_EventConfigurationTr1", "Hi!");
         }
      }
   }

   @Override
   public void render(RenderRequest portletReq, RenderResponse portletResp) throws PortletException, IOException {

      PrintWriter writer = portletResp.getWriter();
      ModuleTestCaseDetails tcd = new ModuleTestCaseDetails();
      RenderParameters renderParams = portletReq.getRenderParameters();
      
      /* TestCase: V3AnnotationPortletApplicationConfigTests_SPEC1_28_EventConfiguration_declaringEvents1 */
      /* Details: "An event with Qname may be declared in the @PortletApplication   */
      /* annotation using @EventDefinition annotation."                             */
      if(renderParams.getValue("tr0")!=null && renderParams.getValue("tr0").equals("true")){
         TestResult result = tcd.getTestResultFailed(V3ANNOTATIONPORTLETAPPLICATIONCONFIGTESTS_SPEC1_28_EVENTCONFIGURATION_DECLARINGEVENTS1);
         result.setTcSuccess(true);
         result.writeTo(writer);
      } else {
         ActionURL aurl = portletResp.createActionURL();
         TestButton tb = new TestButton(
               V3ANNOTATIONPORTLETAPPLICATIONCONFIGTESTS_SPEC1_28_EVENTCONFIGURATION_DECLARINGEVENTS1,
               aurl);
         tb.writeTo(writer);
      }
      
      /* TestCase: V3AnnotationPortletApplicationConfigTests_SPEC1_28_EventConfiguration_declaringEvents2 */
      /* Details: "An event with name may be declared in the @PortletApplication    */
      /* annotation using @EventDefinition annotation and leaving namespaceURI      */
      /* empty."                                                               */
      if(renderParams.getValue("tr1")!=null && renderParams.getValue("tr1").equals("true")){
         TestResult result = tcd.getTestResultFailed(V3ANNOTATIONPORTLETAPPLICATIONCONFIGTESTS_SPEC1_28_EVENTCONFIGURATION_DECLARINGEVENTS2);
         result.setTcSuccess(true);
         result.writeTo(writer);
      } else {
         ActionURL aurl = portletResp.createActionURL();
         TestButton tb = new TestButton(
               V3ANNOTATIONPORTLETAPPLICATIONCONFIGTESTS_SPEC1_28_EVENTCONFIGURATION_DECLARINGEVENTS2,
               aurl);
         tb.writeTo(writer);
      }

   }
   
   @EventMethod(
      portletName = "AnnotationPortletApplicationConfigTests_SPEC1_28_EventConfiguration", 
      processingEvents = @PortletQName(
         localPart = "AnnotationPortletApplicationConfigTests_SPEC1_28_EventConfigurationTr0", 
         namespaceURI = "http://www.apache.org/portals/pluto/portlet-tck_3.0"
      )
   )
   public void processEventTr0(EventRequest portletReq, EventResponse portletResp)
            throws PortletException, IOException {
      
      MutableRenderParameters mutableRenderParameters = portletResp.getRenderParameters();
      mutableRenderParameters.setValue("tr0", "true");
   }
   
   @EventMethod(
      portletName = "AnnotationPortletApplicationConfigTests_SPEC1_28_EventConfiguration", 
      processingEvents = @PortletQName(
         localPart = "AnnotationPortletApplicationConfigTests_SPEC1_28_EventConfigurationTr1", 
         namespaceURI = ""
      )
   )
   public void processEventTr1(EventRequest portletReq, EventResponse portletResp)
         throws PortletException, IOException {
      MutableRenderParameters mutableRenderParameters = portletResp.getRenderParameters();
      mutableRenderParameters.setValue("tr1", "true");

   }

}
