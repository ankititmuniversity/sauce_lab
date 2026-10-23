package retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	int count = 0;
	public final int MAX_RETRY = 2;
	@Override
	public boolean retry(ITestResult result) {
		if(count<MAX_RETRY) {
			System.out.println("Retried method name is: "+result.getMethod().getMethodName());
			count++;
			return true;
		}
		return false;
	}

}
