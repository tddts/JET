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

package com.github.tddts.evetrader.config.spring.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker for methods implementing REST client logic.
 * Method marked with such annotation will be repeated if REST-specific exception is thrown.
 * Class in which this method defined should have {@link RestClient} annotation.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 * @see com.github.tddts.evetrader.config.aspect.RetryAnnotationAspect
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {

  /**
   * Timeout to wait before each retry.
   */
  int timeout() default 100;

  /**
   * Maximum number of retries.
   */
  int retries() default 3;
}
