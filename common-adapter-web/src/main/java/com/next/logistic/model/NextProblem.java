package com.next.logistic.model;

import java.net.URI;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.zalando.problem.Problem;
import org.zalando.problem.StatusType;

public class NextProblem implements Problem {

    private final Map<String, Object> attributes;

    public NextProblem(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public URI getType() {
        return null;
    }

    @Override
    public @Nullable String getTitle() {
        return null;
    }

    @Override
    public @Nullable StatusType getStatus() {
        return null;
    }

    @Override
    public @Nullable String getDetail() {
        return null;
    }

    @Override
    public @Nullable URI getInstance() {
        return null;
    }

    @Override
    public Map<String, Object> getParameters() {
        return this.attributes;
    }
}
