// This file is auto-generated, don't edit it. Thanks.
package Test.ali;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.*;
import com.aliyun.sdk.service.dysmsapi20170525.models.*;
import com.google.gson.Gson;
import darabonba.core.client.ClientOverrideConfiguration;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class SendSms {
        public static void main(String[] args) throws Exception {

            // HttpClient Configuration
        /*HttpClient httpClient = new ApacheAsyncHttpClientBuilder()
                .connectionTimeout(Duration.ofSeconds(10)) // Set the connection timeout time, the default is 10 seconds
                .responseTimeout(Duration.ofSeconds(10)) // Set the response timeout time, the default is 20 seconds
                .maxConnections(128) // Set the connection pool size
                .maxIdleTimeOut(Duration.ofSeconds(50)) // Set the connection pool timeout, the default is 30 seconds
                // Configure the proxy
                .proxy(new ProxyOptions(ProxyOptions.Type.HTTP, new InetSocketAddress("<your-proxy-hostname>", 9001))
                        .setCredentials("<your-proxy-username>", "<your-proxy-password>"))
                // If it is an https connection, you need to configure the certificate, or ignore the certificate(.ignoreSSL(true))
                .x509TrustManagers(new X509TrustManager[]{})
                .keyManagers(new KeyManager[]{})
                .ignoreSSL(false)
                .build();*/

            // Configure Credentials authentication information, including ak, secret, token
            StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                    // Please ensure that the environment variables ALIBABA_CLOUD_ACCESS_KEY_ID and ALIBABA_CLOUD_ACCESS_KEY_SECRET are set.
                    .accessKeyId(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"))
                    .accessKeySecret(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"))
                    //.securityToken(System.getenv("ALIBABA_CLOUD_SECURITY_TOKEN")) // use STS token
                    .build());

            // Configure the Client
            AsyncClient client = AsyncClient.builder()
                    .region("cn-hangzhou") // Region ID
                    //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                    .credentialsProvider(provider)
                    //.serviceConfiguration(Configuration.create()) // Service-level configuration
                    // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                    .overrideConfiguration(
                            ClientOverrideConfiguration.create()
                                    // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
                                    .setEndpointOverride("dysmsapi.aliyuncs.com")
                            //.setConnectTimeout(Duration.ofSeconds(30))
                    )
                    .build();

            /*随机6位数生成*/
            Random random = new Random();
            StringBuilder randomCode = new StringBuilder ( );

            for (int i = 0; i < 6; i++) {
                // 生成0-9之间的随机数并直接拼接到字符串
                randomCode.append ( random.nextInt ( 10 ) );
            }

            // Parameter settings for API request
            SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                    .signName("阿里云短信测试")
                    .templateCode("SMS_154950909")
                    .phoneNumbers("13275737913")
                    .templateParam("{\"code\":\"" + randomCode + "\"}")
                    // Request-level configuration rewrite, can set Http request parameters, etc.
                    // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
                    .build();

            // Asynchronously get the return value of the API request
            CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
            // Synchronously get the return value of the API request
            SendSmsResponse resp = response.get();
            System.out.println(new Gson().toJson(resp));
            // Asynchronous processing of return values
        /*response.thenAccept(resp -> {
            System.out.println(new Gson().toJson(resp));
        }).exceptionally(throwable -> { // Handling exceptions
            System.out.println(throwable.getMessage());
            return null;
        });*/

            // Finally, close the client
            client.close();
        }

    }
