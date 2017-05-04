package test;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entity.EmployeesModel;
import test.EmployeeManagerRemote;

public class ClientTest {

	public static void main(String[] args) throws Exception {
		Context context = createRemoteEjbContext("localhost", "8080");
		EmployeeManagerRemote remote = (EmployeeManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//EmployeeManagerBean!test.EmployeeManagerRemote");
		System.out.println(remote.tstPlus("Hello", "Jaro"));

		List<EmployeesModel> list_employees = remote.getAllEmployees();
		System.out.println(list_employees.size());
		for (EmployeesModel employee : list_employees){
			System.out.println("(" + employee.getId() + ", " + employee.getFirst_name() + ", " + employee.getLast_name() + ")");
		}
	}

	/**
	 * Vytvorenie kontextu pre spojenie sa s JBoss aplikacnym serverom
	 * MAGIC
	 * @param host
	 * @param port
	 * @return
	 * @throws NamingException
	 */
	private static Context createRemoteEjbContext(String host, String port) throws NamingException {
		Hashtable<Object, Object> props = new Hashtable<Object, Object>();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		
		props.put("jboss.naming.client.ejb.context", false);
		props.put("org.jboss.ejb.client.scoped.context", true);
 
		props.put("endpoint.name", "client-endpoint");
		props.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", false);
		props.put("remote.connections", "default");
		props.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", false);
 
        props.put(Context.PROVIDER_URL, "http-remoting://" + host + ":" + port);
        props.put("remote.connection.default.host", host);
        props.put("remote.connection.default.port", port);
 
        return new InitialContext(props);
    }
}
