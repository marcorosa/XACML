/*
 * 
 * Copyright (C) 2013-2018 AT&T Intellectual Property.
 *
 * SPDX-License-Identifier: MIT
 *
 */
package com.att.research.xacml.admin.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.att.research.xacml.api.Identifier;
import com.att.research.xacml.api.XACML3;

public class XACMLConstants {

	public static final Set<Identifier> SUBJECT_CATEGORIES = new HashSet<Identifier>();
	public static final Set<Identifier> ACTION_CATEGORIES = new HashSet<Identifier>();
	public static final Set<Identifier> RESOURCE_CATEGORIES = new HashSet<Identifier>();
	public static final Set<Identifier> ENVIRONMENT_CATEGORIES = new HashSet<Identifier>();
	
	public static final Set<Identifier> CATEGORIES = new HashSet<Identifier>();
	static {
		SUBJECT_CATEGORIES.addAll(Arrays.asList(XACML3.ID_SUBJECT_CATEGORY_ACCESS_SUBJECT,
												XACML3.ID_SUBJECT_CATEGORY_CODEBASE,
												XACML3.ID_SUBJECT_CATEGORY_INTERMEDIARY_SUBJECT,
												XACML3.ID_SUBJECT_CATEGORY_RECIPIENT_SUBJECT,
												XACML3.ID_SUBJECT_CATEGORY_REQUESTING_MACHINE)
												);
		
		ACTION_CATEGORIES.addAll(Arrays.asList(
												XACML3.ID_ATTRIBUTE_CATEGORY_ACTION)
												);
		
		RESOURCE_CATEGORIES.addAll(Arrays.asList(
												XACML3.ID_ATTRIBUTE_CATEGORY_RESOURCE)
												);
		
		ENVIRONMENT_CATEGORIES.addAll(Arrays.asList(
												XACML3.ID_ATTRIBUTE_CATEGORY_ENVIRONMENT)
												);
		
		CATEGORIES.addAll(SUBJECT_CATEGORIES);
		CATEGORIES.addAll(ACTION_CATEGORIES);
		CATEGORIES.addAll(RESOURCE_CATEGORIES);
		CATEGORIES.addAll(ENVIRONMENT_CATEGORIES);
		
	}
	public static final Set<Identifier> DATATYPES = new HashSet<Identifier>();
	static {
		DATATYPES.addAll(Arrays.asList(XACML3.ID_DATATYPE_STRING,
										XACML3.ID_DATATYPE_BOOLEAN,
										XACML3.ID_DATATYPE_INTEGER,
										XACML3.ID_DATATYPE_DOUBLE,
										XACML3.ID_DATATYPE_TIME,
										XACML3.ID_DATATYPE_DATE,
										XACML3.ID_DATATYPE_DATETIME,
										XACML3.ID_DATATYPE_DAYTIMEDURATION,
										XACML3.ID_DATATYPE_YEARMONTHDURATION,
										XACML3.ID_DATATYPE_ANYURI,
										XACML3.ID_DATATYPE_HEXBINARY,
										XACML3.ID_DATATYPE_BASE64BINARY,
										XACML3.ID_DATATYPE_RFC822NAME,
										XACML3.ID_DATATYPE_X500NAME,
										XACML3.ID_DATATYPE_IPADDRESS,
										XACML3.ID_DATATYPE_DNSNAME));
	}
	
	public static final Set<Identifier> POLICY_ALGORITHMS = new HashSet<Identifier>();
	static {
		POLICY_ALGORITHMS.addAll(Arrays.asList(
				XACML3.ID_POLICY_DENY_OVERRIDES,
				XACML3.ID_POLICY_DENY_UNLESS_PERMIT,
				XACML3.ID_POLICY_FIRST_APPLICABLE,
				XACML3.ID_POLICY_ON_PERMIT_APPLY_SECOND,
				XACML3.ID_POLICY_ONLY_ONE_APPLICABLE,
				XACML3.ID_POLICY_ORDERED_DENY_OVERRIDES,
				XACML3.ID_POLICY_ORDERED_PERMIT_OVERRIDES,
				XACML3.ID_POLICY_PERMIT_OVERRIDES,
				XACML3.ID_POLICY_PERMIT_UNLESS_DENY
				));
	}
	
	public static final Set<Identifier> RULE_ALGORITHMS = new HashSet<Identifier>();
	static {
		RULE_ALGORITHMS.addAll(Arrays.asList(
				XACML3.ID_RULE_DENY_OVERRIDES,
				XACML3.ID_RULE_DENY_UNLESS_PERMIT,
				XACML3.ID_RULE_FIRST_APPLICABLE,
				XACML3.ID_RULE_ONLY_ONE_APPLICABLE,
				XACML3.ID_RULE_ORDERED_DENY_OVERRIDES,
				XACML3.ID_RULE_ORDERED_PERMIT_OVERRIDES,
				XACML3.ID_RULE_PERMIT_OVERRIDES,
				XACML3.ID_RULE_PERMIT_UNLESS_DENY
				));
	}

	public static final Set<Identifier> STANDARD_ATTRIBUTES = new HashSet<Identifier>();
	static {
		STANDARD_ATTRIBUTES.addAll(Arrays.asList(
				XACML3.ID_SUBJECT_SUBJECT_ID,
				XACML3.ID_SUBJECT_SUBJECT_ID_QUALIFIER,
				XACML3.ID_SUBJECT_KEY_INFO,
				XACML3.ID_SUBJECT_AUTHENTICATION_TIME,
				XACML3.ID_SUBJECT_AUTHENTICATION_METHOD,
				XACML3.ID_SUBJECT_REQUEST_TIME,
				XACML3.ID_SUBJECT_SESSION_START_TIME,
				XACML3.ID_SUBJECT_AUTHN_LOCALITY_IP_ADDRESS,
				XACML3.ID_SUBJECT_AUTHN_LOCALITY_DNS_NAME,
				XACML3.ID_SUBJECT_ROLE,
				XACML3.ID_ACTION_ACTION_ID,
				XACML3.ID_ACTION_IMPLIED_ACTION,
				XACML3.ID_RESOURCE_RESOURCE_ID,
				XACML3.ID_RESOURCE_RESOURCE_LOCATION,
				XACML3.ID_RESOURCE_SIMPLE_FILE_NAME,
				XACML3.ID_ENVIRONMENT_CURRENT_DATE,
				XACML3.ID_ENVIRONMENT_CURRENT_TIME,
				XACML3.ID_ENVIRONMENT_CURRENT_DATETIME
				));
	}
	
	public static final Map<Identifier, Set<Identifier>> MAP_STANDARD_CATEGORIES = new HashMap<Identifier, Set<Identifier>>();
	static {
		MAP_STANDARD_CATEGORIES.put(XACML3.ID_SUBJECT_CATEGORY_ACCESS_SUBJECT, new HashSet<Identifier>(Arrays.asList(
				XACML3.ID_SUBJECT_SUBJECT_ID,
				XACML3.ID_SUBJECT_SUBJECT_ID_QUALIFIER,
				XACML3.ID_SUBJECT_KEY_INFO,
				XACML3.ID_SUBJECT_AUTHENTICATION_TIME,
				XACML3.ID_SUBJECT_AUTHENTICATION_METHOD,
				XACML3.ID_SUBJECT_REQUEST_TIME,
				XACML3.ID_SUBJECT_SESSION_START_TIME,
				XACML3.ID_SUBJECT_AUTHN_LOCALITY_IP_ADDRESS,
				XACML3.ID_SUBJECT_AUTHN_LOCALITY_DNS_NAME,
				XACML3.ID_SUBJECT_ROLE
				)));
		MAP_STANDARD_CATEGORIES.put(XACML3.ID_SUBJECT_CATEGORY_CODEBASE, new HashSet<Identifier>(Arrays.asList(
				XACML3.ID_SUBJECT_SUBJECT_ID,
				XACML3.ID_SUBJECT_SUBJECT_ID_QUALIFIER,
				XACML3.ID_SUBJECT_KEY_INFO,
				XACML3.ID_SUBJECT_AUTHENTICATION_TIME,
				XACML3.ID_SUBJECT_AUTHENTICATION_METHOD,
				XACML3.ID_SUBJECT_REQUEST_TIME,
				XACML3.ID_SUBJECT_SESSION_START_TIME,
				XACML3.ID_SUBJECT_AUTHN_LOCALITY_IP_ADDRESS,
				XACML3.ID_SUBJECT_AUTHN_LOCALITY_DNS_NAME,
				XACML3.ID_SUBJECT_ROLE
				)));
		MAP_STANDARD_CATEGORIES.put(XACML3.ID_SUBJECT_CATEGORY_INTERMEDIARY_SUBJECT, new HashSet<Identifier>(Arrays.asList(
				XACML3.ID_SUBJECT_SUBJECT_ID,
				XACML3.ID_SUBJECT_SUBJECT_ID_QUALIFIER,
				XACML3.ID_SUBJECT_KEY_INFO,
				XACML3.ID_SUBJECT_AUTHENTICATION_TIME,
				XACML3.ID_SUBJECT_AUTHENTICATION_METHOD,
				XACML3.ID_SUBJECT_REQUEST_TIME,
				XACML3.ID_SUBJECT_SESSION_START_TIME,
				XACML3.ID_SUBJECT_AUTHN_LOCALITY_IP_ADDRESS,
				XACML3.ID_SUBJECT_AUTHN_LOCALITY_DNS_NAME,
				XACML3.ID_SUBJECT_ROLE
				)));
		MAP_STANDARD_CATEGORIES.put(XACML3.ID_SUBJECT_CATEGORY_RECIPIENT_SUBJECT, new HashSet<Identifier>(Arrays.asList(
				XACML3.ID_SUBJECT_SUBJECT_ID,
				XACML3.ID_SUBJECT_SUBJECT_ID_QUALIFIER,
				XACML3.ID_SUBJECT_KEY_INFO,
				XACML3.ID_SUBJECT_AUTHENTICATION_TIME,
				XACML3.ID_SUBJECT_AUTHENTICATION_METHOD,
				XACML3.ID_SUBJECT_REQUEST_TIME,
				XACML3.ID_SUBJECT_SESSION_START_TIME,
				XACML3.ID_SUBJECT_AUTHN_LOCALITY_IP_ADDRESS,
				XACML3.ID_SUBJECT_AUTHN_LOCALITY_DNS_NAME,
				XACML3.ID_SUBJECT_ROLE
				)));
		MAP_STANDARD_CATEGORIES.put(XACML3.ID_SUBJECT_CATEGORY_REQUESTING_MACHINE, new HashSet<Identifier>(Arrays.asList(
				XACML3.ID_SUBJECT_SUBJECT_ID,
				XACML3.ID_SUBJECT_SUBJECT_ID_QUALIFIER,
				XACML3.ID_SUBJECT_KEY_INFO,
				XACML3.ID_SUBJECT_AUTHENTICATION_TIME,
				XACML3.ID_SUBJECT_AUTHENTICATION_METHOD,
				XACML3.ID_SUBJECT_REQUEST_TIME,
				XACML3.ID_SUBJECT_SESSION_START_TIME,
				XACML3.ID_SUBJECT_AUTHN_LOCALITY_IP_ADDRESS,
				XACML3.ID_SUBJECT_AUTHN_LOCALITY_DNS_NAME,
				XACML3.ID_SUBJECT_ROLE
				)));
		MAP_STANDARD_CATEGORIES.put(XACML3.ID_ATTRIBUTE_CATEGORY_ACTION, new HashSet<Identifier>(Arrays.asList(
				XACML3.ID_ACTION_ACTION_ID,
				XACML3.ID_ACTION_IMPLIED_ACTION
				)));
		MAP_STANDARD_CATEGORIES.put(XACML3.ID_ATTRIBUTE_CATEGORY_RESOURCE, new HashSet<Identifier>(Arrays.asList(
				XACML3.ID_RESOURCE_RESOURCE_ID,
				XACML3.ID_RESOURCE_RESOURCE_LOCATION,
				XACML3.ID_RESOURCE_SIMPLE_FILE_NAME
				)));
		MAP_STANDARD_CATEGORIES.put(XACML3.ID_ATTRIBUTE_CATEGORY_ENVIRONMENT, new HashSet<Identifier>(Arrays.asList(
				XACML3.ID_ENVIRONMENT_CURRENT_DATE,
				XACML3.ID_ENVIRONMENT_CURRENT_TIME,
				XACML3.ID_ENVIRONMENT_CURRENT_DATETIME
				)));
	}
	
	public static String extractShortName(String xacmlID) {
		if (xacmlID == null) {
			return null;
		}
		if (xacmlID.startsWith("http:")) {
			String [] parts = xacmlID.split("[#]");
			if (parts != null && parts.length > 0) {
				return parts[parts.length - 1];
			}
			return null;
		}
		if (xacmlID.startsWith("urn") || xacmlID.contains(":")) {
			String[] parts = xacmlID.split("[:]");
			
			if (parts != null && parts.length > 0) {
				return parts[parts.length - 1];
			}
		}	
		return null;
	}
}
