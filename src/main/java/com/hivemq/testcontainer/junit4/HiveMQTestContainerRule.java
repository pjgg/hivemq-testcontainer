package com.hivemq.testcontainer.junit4;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.testcontainer.core.HiveMQExtension;
import com.hivemq.testcontainer.core.HiveMQTestContainer;
import com.hivemq.testcontainer.core.HiveMQTestContainerCore;
import org.junit.runner.Description;
import org.slf4j.event.Level;
import org.testcontainers.containers.FailureDetectingExternalResource;

import java.io.File;
import java.time.Duration;

/**
 * @author Yannick Weber
 */
public class HiveMQTestContainerRule extends FailureDetectingExternalResource implements HiveMQTestContainer {

    private final @NotNull HiveMQTestContainerCore core;

    public HiveMQTestContainerRule(
            final @NotNull String image,
            final @NotNull String tag) {

        core = new HiveMQTestContainerCore(image, tag);
    }

    public HiveMQTestContainerRule() {
        core = new HiveMQTestContainerCore();
    }

    @Override
    protected void starting(final @NotNull Description description) {
        core.start();
    }

    @Override
    protected void finished(final @NotNull Description description) {
        core.stop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule withExtension(final @NotNull HiveMQExtension hiveMQExtension) {
        core.withExtension(hiveMQExtension);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule withExtension(final @NotNull File extensionDirectory) {
        core.withExtension(extensionDirectory);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule withLogLevel(final @NotNull Level logLevel) {
        core.withLogLevel(logLevel);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule withDebugging(final int debuggingPortHost) {
        core.withDebugging(debuggingPortHost);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule withDebugging() {
        core.withDebugging();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule withLicense(final @NotNull File licence) {
        core.withLicense(licence);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule withHiveMQConfig(final @NotNull File config) {
        core.withHiveMQConfig(config);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule withFileInExtensionHomeFolder(
            final @NotNull File file,
            final @NotNull String extensionId,
            final @NotNull String pathInExtensionHome) {

        core.withFileInExtensionHomeFolder(file, extensionId, pathInExtensionHome);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule withFileInExtensionHomeFolder(
            final @NotNull File file,
            final @NotNull String extensionId) {

        core.withFileInExtensionHomeFolder(file, extensionId);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule withFileInHomeFolder(
            final @NotNull File file,
            final @NotNull String pathInExtensionHome) {

        core.withFileInHomeFolder(file, pathInExtensionHome);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule withFileInHomeFolder(
            final @NotNull File file) {

        core.withFileInHomeFolder(file);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule disableExtension(final @NotNull HiveMQExtension hiveMQExtension) {
        core.disableExtension(hiveMQExtension);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule disableExtension(
            final @NotNull HiveMQExtension hiveMQExtension,
            final @NotNull Duration timeOut) {

        core.disableExtension(hiveMQExtension, timeOut);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule enableExtension(final @NotNull HiveMQExtension hiveMQExtension) {
        core.enableExtension(hiveMQExtension);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule enableExtension(
            final @NotNull HiveMQExtension hiveMQExtension,
            final @NotNull Duration timeOut) {

        core.enableExtension(hiveMQExtension, timeOut);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule withControlCenter(final int controlCenterPort) {
        core.withControlCenter(controlCenterPort);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule withControlCenter() {
        core.withControlCenter();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull HiveMQTestContainerRule silent(final boolean silent) {
        core.silent(silent);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMqttPort() {
        return core.getMqttPort();
    }

    /**
     * Returns the underlying {@link HiveMQTestContainerCore}.
     * This is useful for extending the behaviour of the container.
     *
     * @return the HiveMQTestContainerCore
     */
    public @NotNull HiveMQTestContainerCore getCore() {
        return core;
    }
}
