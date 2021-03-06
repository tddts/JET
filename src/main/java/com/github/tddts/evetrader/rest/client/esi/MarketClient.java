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

package com.github.tddts.evetrader.rest.client.esi;

import com.github.tddts.evetrader.rest.RestResponse;
import com.github.tddts.evetrader.consts.OrderType;
import com.github.tddts.evetrader.model.client.esi.market.MarketHistory;
import com.github.tddts.evetrader.model.client.esi.market.MarketOrder;
import com.github.tddts.evetrader.model.client.esi.market.MarketPrice;

import java.util.List;

/**
 * {@code MarketClient} represents a REST client which deals with in-game market through
 * OpenAPI for EVE Online
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface MarketClient {

  RestResponse<List<MarketOrder>> getOrders(OrderType orderType, long regionId, int page);

  RestResponse<List<MarketPrice>> getAllItemPrices();

  RestResponse<List<MarketHistory>> getItemHistory(int type_id, int region_id);
}
