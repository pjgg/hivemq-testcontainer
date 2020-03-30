package com.hivemq.testcontainer.junit5;

import com.hivemq.extension.sdk.api.ExtensionMain;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.interceptor.publish.PublishInboundInterceptor;
import com.hivemq.extension.sdk.api.parameter.ExtensionStartInput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStartOutput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStopInput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStopOutput;
import com.hivemq.extension.sdk.api.services.Services;
import com.hivemq.extension.sdk.api.services.intializer.ClientInitializer;
import com.hivemq.testcontainer.util.TestPublishModifiedUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Yannick Weber
 */
public class ContainerWithFileInHomeIT {

    @RegisterExtension
    public final @NotNull HiveMQTestContainerExtension extension =
            new HiveMQTestContainerExtension()
                    .withExtension(
                            "extension-1",
                            "my-extension",
                            "1.0",
                            100,
                            1000,
                            FileCheckerExtension.class)
                    .withFileInHomeFolder(
                            new File("src/test/resources/additionalFile.txt"),
                            "/additionalFiles/")
                    .withDebugging();

    @Test()
    @Timeout(value = 5, unit = TimeUnit.MINUTES)
    void test_single_class_extension() throws ExecutionException, InterruptedException {
        TestPublishModifiedUtil.testPublishModified(extension.getMqttPort());
    }

    public static class FileCheckerExtension implements ExtensionMain {

        @Override
        public void extensionStart(@NotNull ExtensionStartInput extensionStartInput, @NotNull ExtensionStartOutput extensionStartOutput) {

            final PublishInboundInterceptor publishInboundInterceptor = (publishInboundInput, publishInboundOutput) -> {

                final File homeFolder = extensionStartInput.getServerInformation().getHomeFolder();

                final File additionalFile = new File(homeFolder, "additionalFiles/additionalFile.txt");

                if (additionalFile.exists()) {
                    publishInboundOutput.getPublishPacket().setPayload(ByteBuffer.wrap("modified".getBytes(StandardCharsets.UTF_8)));
                }
            };

            final ClientInitializer clientInitializer = (initializerInput, clientContext) -> clientContext.addPublishInboundInterceptor(publishInboundInterceptor);

            Services.initializerRegistry().setClientInitializer(clientInitializer);
        }

        @Override
        public void extensionStop(@NotNull ExtensionStopInput extensionStopInput, @NotNull ExtensionStopOutput extensionStopOutput) {

        }
    }

}
