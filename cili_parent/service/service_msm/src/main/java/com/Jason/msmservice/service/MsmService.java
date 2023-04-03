package com.Jason.msmservice.service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface MsmService {
    boolean send(String phone, Map<String, Object> param) throws ExecutionException, InterruptedException;
}
