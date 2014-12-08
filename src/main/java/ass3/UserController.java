package ass3;

import java.net.URI;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.justinsb.etcd.EtcdClient;
import com.justinsb.etcd.EtcdResult;

/**
 * @author Varun User Controller class
 */
@RestController
public class UserController extends WebMvcConfigurerAdapter {

//	EtcdClient client = new EtcdClient(URI.create("http://127.0.0.1:4001/"));
	EtcdClient client = new EtcdClient(URI.create("http://54.183.225.139:4001/"));
// EtcdClient client = new EtcdClient(URI.create("http://54.67.103.220:4001/"));
//	EtcdClient client = new EtcdClient(URI.create(" http://54.67.114.76:4001"));

	@RequestMapping(value = "/api/v1/counter", method = RequestMethod.GET)
	public EtcdResult setAndGet() throws Exception {

		EtcdResult result;
		
		String key = "/009994400";
		System.out.println("before getting key");
		//EtcdResult resultst = this.client.set(key, "1");
		result = this.client.get(key);
		System.out.println("Before : "+result);
		String str = "";
		try {
			System.out.println("inside try");
			str = Integer.toString(Integer.parseInt(result.node.value) + 1);
			System.out.println("String try Value --->>> "+str);
		} catch (Exception exp) {
			str = "1";
		} finally {
			System.out.println("inside finally");
			result = this.client.set(key, str);
			result = this.client.get(key);
			System.out.println("Finally : "+result);
		}
		return result;
	}
}
