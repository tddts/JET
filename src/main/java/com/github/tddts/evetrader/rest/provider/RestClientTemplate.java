/*
 * Copyright 2018 Tigran Dadaiants
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tddts.evetrader.rest.provider;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * {@code RestClientTemplate} is an interface specifying a set of methods for building REST client.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface RestClientTemplate {

  RestOperations restOperations();

  String apiUrl(String resourceAddress);

  UriComponentsBuilder apiUriBuilder(String resourceAddress);

  UriComponentsBuilder authUriBuilder(String resourceAddress);

  HttpEntity<?> jsonEntity(Object body);

  HttpEntity<?> authorizedEntity();

  HttpEntity<?> authorizedEntity(Object body);

  HttpEntity<?> basicAuthorizedEntity();

  HttpEntity<?> bearerAuthorizedEntity();
}
