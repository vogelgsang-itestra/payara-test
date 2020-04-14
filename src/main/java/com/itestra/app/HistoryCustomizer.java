package com.itestra.app;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.history.HistoryPolicy;


public class HistoryCustomizer implements DescriptorCustomizer {

    @Override
    public void customize(final ClassDescriptor descriptor) {

        HistoryPolicy policy = new CustomHistoryPolicy();
        policy.addHistoryTableName(descriptor.getTableName() + "_HISTORY");
        policy.addStartFieldName("valid_from");
        policy.addEndFieldName("valid_to");

        policy.getMapping();

        descriptor.setHistoryPolicy(policy);
    }
}