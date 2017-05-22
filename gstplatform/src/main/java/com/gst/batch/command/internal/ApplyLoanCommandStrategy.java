/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.gst.batch.command.internal;

import javax.ws.rs.core.UriInfo;

import com.gst.batch.command.CommandStrategy;
import com.gst.batch.domain.BatchRequest;
import com.gst.batch.domain.BatchResponse;
import com.gst.batch.exception.ErrorHandler;
import com.gst.batch.exception.ErrorInfo;
import com.gst.portfolio.loanaccount.api.LoansApiResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implements {@link com.gst.batch.command.CommandStrategy} and
 * applies a new loan on an existing client. It passes the contents of the body
 * from the BatchRequest to
 * {@link com.gst.portfolio.client.api.LoansApiResource} and gets back
 * the response. This class will also catch any errors raised by
 * {@link com.gst.portfolio.client.api.LoansApiResource} and map those
 * errors to appropriate status codes in BatchResponse.
 * 
 * @author Rishabh Shukla
 * 
 * @see com.gst.batch.command.CommandStrategy
 * @see com.gst.batch.domain.BatchRequest
 * @see com.gst.batch.domain.BatchResponse
 */
@Component
public class ApplyLoanCommandStrategy implements CommandStrategy {

    private final LoansApiResource loansApiResource;

    @Autowired
    public ApplyLoanCommandStrategy(final LoansApiResource loansApiResource) {
        this.loansApiResource = loansApiResource;
    }

    @Override
    public BatchResponse execute(BatchRequest request, @SuppressWarnings("unused") UriInfo uriInfo) {

        final BatchResponse response = new BatchResponse();
        final String responseBody;

        response.setRequestId(request.getRequestId());
        response.setHeaders(request.getHeaders());

        // Try-catch blocks to map exceptions to appropriate status codes
        try {

            // Calls 'SubmitLoanFunction' function from 'LoansApiResource' to
            // Apply Loan to an existing client
            responseBody = loansApiResource.calculateLoanScheduleOrSubmitLoanApplication(null, null, request.getBody());

            response.setStatusCode(200);
            // Sets the body of the response after loan is successfully applied
            response.setBody(responseBody);

        } catch (RuntimeException e) {

            // Gets an object of type ErrorInfo, containing information about
            // raised exception
            ErrorInfo ex = ErrorHandler.handler(e);

            response.setStatusCode(ex.getStatusCode());
            response.setBody(ex.getMessage());
        }

        return response;
    }
}
