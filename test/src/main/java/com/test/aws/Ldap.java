package com.test.aws;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;

public class Ldap {

	public static void main(String[] args) {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public static void init() throws Exception {
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");//LDAP访问安全级别："none","simple","strong"
        env.put(Context.SECURITY_PRINCIPAL, "test_slurm");
        env.put(Context.SECURITY_CREDENTIALS, "7y5edyp6TJeX");
        env.put(Context.PROVIDER_URL, "LDAP://jndc01.alchip.com"); // "LDAP://jndc01.alchip.com:389"
    	DirContext dc = new InitialLdapContext(env, null);
    	dc.getEnvironment();
    }

}
