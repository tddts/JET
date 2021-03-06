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

package com.github.tddts.evetrader.view.sprix;

import com.github.tddts.evetrader.view.fx.dialog.ExceptionDialog;
import com.github.tddts.sprix.beans.SprixDialogProvider;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code SprixUncaughtExceptionHandler} is a simple implementation of {@link Thread.UncaughtExceptionHandler}
 * that shows dialog with error message.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class SprixUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

  private final Logger logger = LogManager.getLogger(Thread.UncaughtExceptionHandler.class);

  private SprixDialogProvider dialogProvider;

  public SprixUncaughtExceptionHandler(SprixDialogProvider dialogProvider) {
    this.dialogProvider = dialogProvider;
  }

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    logger.error(e.getMessage(), e);
    Platform.runLater(() -> {
      ExceptionDialog exceptionDialog = dialogProvider.getDialog(ExceptionDialog.class);
      if (!exceptionDialog.isShowing()){
        exceptionDialog.setThrowable(e);
        exceptionDialog.showAndWait();
      }
    });
  }
}
