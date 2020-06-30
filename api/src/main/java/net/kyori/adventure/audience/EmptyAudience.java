/*
 * This file is part of adventure, licensed under the MIT License.
 *
 * Copyright (c) 2017-2020 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.adventure.audience;

import net.kyori.adventure.sound.SoundStop;
import net.kyori.adventure.text.Component;
import org.checkerframework.checker.nullness.qual.NonNull;
import java.util.function.Consumer;

/* package */ interface EmptyAudience extends Audience.Everything {
  Audience.Everything INSTANCE = new EmptyAudience() {};

  @Override
  default <T extends Audience> @NonNull Audience perform(final @NonNull Class<T> type, final @NonNull Consumer<T> action) {
    return this;
  }

  @Override
  default void sendMessage(final @NonNull Component message) {
  }

  @Override
  default void sendActionBar(final @NonNull Component message) {
  }

  @Override
  default void showTitle(final @NonNull Title title) {
  }

  @Override
  default void clearTitle() {
  }

  @Override
  default void resetTitle() {
  }

  @Override
  default void showBossBar(final @NonNull BossBar bar) {
  }

  @Override
  default void hideBossBar(final @NonNull BossBar bar) {
  }

  @Override
  default void playSound(final @NonNull Sound sound) {
  }

  @Override
  default void playSound(final @NonNull Sound sound, final double x, final double y, final double z) {
  }

  @Override
  default void stopSound(final @NonNull SoundStop stop) {
  }

  @Override
  default void openBook(final @NonNull Book book) {
  }
}
