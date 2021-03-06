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

package com.github.tddts.evetrader.view.fx.tools.message;

import javafx.util.StringConverter;

/**
 * {@code MessageProvider} is a class that provides messages for various types of objects.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface MessageProvider {

  /**
   * Get message for given key.
   *
   * @param key message key
   * @return message associated with given key
   */
  String getMessage(String key);

  /**
   * Get message for given Enum object.
   *
   * @param object Enum object
   * @return message for given object
   */
  String getMessage(Enum object);

  /**
   * Get {@link StringConverter} for given array of Enums.
   *
   * @param values array of Enums.
   * @param <T> type of Enum
   * @return string converter for given array
   */
  <T extends Enum> StringConverter<T> getConverter(T[] values);
}
