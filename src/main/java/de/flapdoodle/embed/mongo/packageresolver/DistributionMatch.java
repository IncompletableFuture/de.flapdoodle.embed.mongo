/**
 * Copyright (C) 2011
 *   Michael Mosmann <michael@mosmann.de>
 *   Martin Jöhren <m.joehren@googlemail.com>
 *
 * with contributions from
 * 	konstantin-ba@github,Archimedes Trajano	(trajano@github)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.flapdoodle.embed.mongo.packageresolver;

import java.util.Arrays;

public interface DistributionMatch {
  boolean match(de.flapdoodle.embed.process.distribution.Distribution distribution);

  default DistributionMatch andThen(DistributionMatch other) {
    DistributionMatch that = this;
    return dist -> that.match(dist) && other.match(dist);
  }

  static DistributionMatch all() {
    return __ -> true;
  }

  static DistributionMatch any(DistributionMatch ... matcher) {
    return dist -> Arrays.stream(matcher).anyMatch(m -> m.match(dist));
  }
}
