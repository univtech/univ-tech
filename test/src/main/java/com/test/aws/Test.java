package com.test.aws;

import java.util.ArrayList;
import java.util.List;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.costexplorer.CostExplorerClient;
import software.amazon.awssdk.services.costexplorer.model.DateInterval;
import software.amazon.awssdk.services.costexplorer.model.DimensionValues;
import software.amazon.awssdk.services.costexplorer.model.Expression;
import software.amazon.awssdk.services.costexplorer.model.GetReservationUtilizationRequest;
import software.amazon.awssdk.services.costexplorer.model.GetReservationUtilizationResponse;
import software.amazon.awssdk.services.costexplorer.model.Granularity;
import software.amazon.awssdk.services.organizations.OrganizationsClient;
import software.amazon.awssdk.services.organizations.model.Account;
import software.amazon.awssdk.services.organizations.model.ListAccountsRequest;
import software.amazon.awssdk.services.organizations.model.ListAccountsResponse;

public class Test {

	public static void main(String[] args) {
		AwsBasicCredentials credentials = AwsBasicCredentials.create("AKIA6DQ3AYNFMJOPPCEB", "6y2MTJ8FygKgxq4+Nec+xaFH7ebZFNIBCn4mZyRB"); // 969645540170
//		AwsBasicCredentials credentials = AwsBasicCredentials.create("AKIA6CNQ6ELCFQLDGCI4", "c4OVXQaJxLWUfZ2wEiYhgpiY5Sx0HoZZpphvLtw+");
//		AwsBasicCredentials credentials = AwsBasicCredentials.create("AKIAYNAYTFGQQF7ODCPU", "Rho+adMMGLY1juDoVDx0yReHnATHVgjBrnoLBQAS");
//		AwsBasicCredentials credentials = AwsBasicCredentials.create("AKIAYCAV3TFSDZYLEG4N", "SYH0tMmEcHLuwEobGxF1/bQjbIry3szPlWs008L/");
//		AwsBasicCredentials credentials = AwsBasicCredentials.create("AKIAYKHNO2M74XYPGBOV", "NfpEG70BhjsRO6pxsOqAdQjtRmu9KQRfkmPizUm4");

//		AwsBasicCredentials credentials = AwsBasicCredentials.create("AKIAYULUACGRYXOGGPLL", "mAwBzgWXHsllU0KZVQlEWUBWtM6KJhAVAUEUnob5");

		testCostExplorerClient(credentials);
	}

	private static void testOrganizationsClient(AwsBasicCredentials credentials) {
		OrganizationsClient client = OrganizationsClient.builder().region(Region.CN_NORTHWEST_1).credentialsProvider(() -> credentials).build();
//		OrganizationsClient client = OrganizationsClient.builder().region(Region.AWS_CN_GLOBAL).credentialsProvider(() -> credentials).build();
//		OrganizationsClient client = OrganizationsClient.builder().region(Region.AWS_GLOBAL).credentialsProvider(() -> credentials).build();

		ListAccountsResponse response = client.listAccounts(ListAccountsRequest.builder().nextToken(null).build());
		List<Account> accounts = response.accounts();
		for (Account account : accounts) {
			System.out.println(account.id());
		}
	}

	private static void testCostExplorerClient(AwsBasicCredentials credentials) {
//		CostExplorerClient client = CostExplorerClient.builder().region(Region.AWS_CN_GLOBAL).credentialsProvider(() -> credentials).build();  // 成功
		CostExplorerClient client = CostExplorerClient.builder().region(Region.CN_NORTHWEST_1).credentialsProvider(() -> credentials).build(); // 成功
//		CostExplorerClient client = CostExplorerClient.builder().region(Region.CN_NORTH_1).credentialsProvider(() -> credentials).build();     // 失败

		List<String> services = new ArrayList<>();
		services.add("Amazon Elastic Compute Cloud - Compute");
//		services.add("Amazon ElastiCache");
//		services.add("Amazon Elasticsearch Service");
//		services.add("Amazon Redshift");
//		services.add("Amazon Relational Database Service");
		DimensionValues serviceDimension = DimensionValues.builder().key("SERVICE").values(services).build();
		Expression serviceExpression = Expression.builder().dimensions(serviceDimension).build();

		DimensionValues linkedAccountDimension = DimensionValues.builder().key("LINKED_ACCOUNT").values("969645540170").build();
		Expression linkedAccountExpression = Expression.builder().dimensions(linkedAccountDimension).build();

		DateInterval timePeriod = DateInterval.builder().start("2019-10-01").end("2020-09-30").build();
		Expression filter = Expression.builder().and(serviceExpression, linkedAccountExpression).build();

		GetReservationUtilizationRequest request = GetReservationUtilizationRequest.builder().timePeriod(timePeriod).filter(filter).granularity(Granularity.DAILY).nextPageToken(null).build();
		GetReservationUtilizationResponse response = client.getReservationUtilization(request);
		response.total();
	}

}
