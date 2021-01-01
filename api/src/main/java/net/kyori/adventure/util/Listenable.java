/*
 * This file is part of adventure, licensed under the MIT License.
 *
 * Copyright (c) 2017-2021 KyoriPowered
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
package net.kyori.adventure.util;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Something that has listeners.
 *
 * @param <L> the listener type
 * @since 4.0.0
 */
public abstract class Listenable<L> {
  private final List<L> listeners = new CopyOnWriteArrayList<>();

  /**
   * Process an action for each listener.
   *
   * @param consumer the consumer
   * @since 4.0.0
   */
  protected final void forEachListener(final @NonNull Consumer<L> consumer) {
    for(final L listener : this.listeners) {
      consumer.accept(listener);
    }
  }

  /**
   * Adds a listener.
   *
   * @param listener the listener
   * @since 4.0.0
   */
  protected final void addListener0(final @NonNull L listener) {
    this.listeners.add(listener);
  }

  /**
   * Removes a listener.
   *
   * @param listener the listener
   * @since 4.0.0
   */
  protected final void removeListener0(final @NonNull L listener) {
    this.listeners.remove(listener);
  }
}
