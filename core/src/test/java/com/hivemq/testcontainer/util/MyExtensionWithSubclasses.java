/*
 * Copyright 2020 HiveMQ and the HiveMQ Community
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hivemq.testcontainer.util;

import com.hivemq.extension.sdk.api.ExtensionMain;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.parameter.ExtensionStartInput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStartOutput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStopInput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStopOutput;
import com.hivemq.extension.sdk.api.services.Services;
import com.hivemq.extension.sdk.api.services.intializer.ClientInitializer;

/**
 * @author Yannick Weber
 */
public class MyExtensionWithSubclasses implements ExtensionMain {

    @Override
    public void extensionStart(final @NotNull ExtensionStartInput extensionStartInput, final @NotNull ExtensionStartOutput extensionStartOutput) {

        final ClientInitializer clientInitializer = (initializerInput, clientContext) -> clientContext.addPublishInboundInterceptor(new PublishModifier());

        Services.initializerRegistry().setClientInitializer(clientInitializer);
    }

    @Override
    public void extensionStop(final @NotNull ExtensionStopInput extensionStopInput, final @NotNull ExtensionStopOutput extensionStopOutput) {

    }
}
