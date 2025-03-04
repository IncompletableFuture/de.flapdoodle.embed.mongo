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
package de.flapdoodle.embed.mongo.packageresolver.linux;

import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.packageresolver.*;
import de.flapdoodle.embed.process.config.store.*;
import de.flapdoodle.embed.process.distribution.ArchiveType;
import de.flapdoodle.embed.process.distribution.Distribution;
import de.flapdoodle.os.BitSize;
import de.flapdoodle.os.CPUType;
import de.flapdoodle.os.OS;
import de.flapdoodle.os.linux.UbuntuVersion;

import java.util.Optional;

public class UbuntuPackageResolver implements PackageFinder {

  private final Command command;
  private final ImmutablePlatformMatchRules rules;

  public UbuntuPackageResolver(Command command) {
    this.command = command;
    this.rules = rules(command);
  }

  @Override
  public Optional<DistributionPackage> packageFor(Distribution distribution) {
    return rules.packageFor(distribution);
  }

  /*
  -----------------------------------

     */
  private static ImmutablePlatformMatchRules rules(Command command) {
    ImmutableFileSet fileSet = FileSet.builder().addEntry(FileType.Executable, command.commandName()).build();

    /*
      Ubuntu 18.04 ARM 64
      https://fastdl.mongodb.org/linux/mongodb-linux-aarch64-ubuntu1804-{}.tgz
      5.0.2 - 5.0.0, 4.4.9 - 4.4.0, 4.2.16 - 4.2.5, 4.2.3 - 4.2.0
      -----------------------------------
    */
			PlatformMatchRule ubuntu1804arm = PlatformMatchRule.builder()
					.match(DistributionMatch.any(
											VersionRange.of("5.0.0", "5.0.2"),
											VersionRange.of("4.4.0", "4.4.9"),
											VersionRange.of("4.2.5", "4.2.16"),
											VersionRange.of("4.2.0", "4.2.3")
									)
									.andThen(PlatformMatch
											.withOs(OS.Linux)
											.withBitSize(BitSize.B64)
											.withCpuType(CPUType.ARM)
											.withVersion(
													UbuntuVersion.UBUNTU_18_04, UbuntuVersion.UBUNTU_18_10, UbuntuVersion.UBUNTU_19_04, UbuntuVersion.UBUNTU_19_10,
													UbuntuVersion.UBUNTU_20_04, UbuntuVersion.UBUNTU_20_10
											)
									)
					)
					.finder(UrlTemplatePackageResolver.builder()
							.fileSet(fileSet)
							.archiveType(ArchiveType.TGZ)
							.urlTemplate("/linux/mongodb-linux-aarch64-ubuntu1804-{version}.tgz")
							.build())
					.build();

			PlatformMatchRule tools_ubuntu1804arm = PlatformMatchRule.builder()
					.match(DistributionMatch.any(
											VersionRange.of("5.0.0", "5.0.2"),
											VersionRange.of("4.4.0", "4.4.9"),
											VersionRange.of("4.2.5", "4.2.16"),
											VersionRange.of("4.2.0", "4.2.3")
									)
									.andThen(PlatformMatch
											.withOs(OS.Linux)
											.withBitSize(BitSize.B64)
											.withCpuType(CPUType.ARM)
											.withVersion(
													UbuntuVersion.UBUNTU_18_04, UbuntuVersion.UBUNTU_18_10, UbuntuVersion.UBUNTU_19_04, UbuntuVersion.UBUNTU_19_10,
													UbuntuVersion.UBUNTU_20_04, UbuntuVersion.UBUNTU_20_10
											)
									)
					)
					.finder(UrlTemplatePackageResolver.builder()
							.fileSet(fileSet)
							.archiveType(ArchiveType.TGZ)
							.urlTemplate("/tools/db/mongodb-database-tools-ubuntu1804-arm64-{tools.version}.tgz")
							.build())
					.build();

    /*
      Ubuntu 18.04 x64
      https://fastdl.mongodb.org/linux/mongodb-linux-x86_64-ubuntu1804-{}.tgz
      5.0.2 - 5.0.0, 4.4.9 - 4.4.0, 4.2.16 - 4.2.5, 4.2.3 - 4.2.0, 4.0.26 - 4.0.1, 3.6.22 - 3.6.20
     */
			PlatformMatchRule ubuntu1804x64 = PlatformMatchRule.builder()
					.match(DistributionMatch.any(
											VersionRange.of("5.0.0", "5.0.2"),
											VersionRange.of("4.4.0", "4.4.9"),
											VersionRange.of("4.2.5", "4.2.16"),
											VersionRange.of("4.2.0", "4.2.3"),
											VersionRange.of("4.0.1", "4.0.26"),
											VersionRange.of("3.6.20", "3.6.22")
									)
									.andThen(PlatformMatch
											.withOs(OS.Linux)
											.withBitSize(BitSize.B64)
											.withCpuType(CPUType.X86)
											.withVersion(
													UbuntuVersion.UBUNTU_18_04, UbuntuVersion.UBUNTU_18_10, UbuntuVersion.UBUNTU_19_04, UbuntuVersion.UBUNTU_19_10,
													UbuntuVersion.UBUNTU_20_04, UbuntuVersion.UBUNTU_20_10
											)
									)
					)
					.finder(UrlTemplatePackageResolver.builder()
							.fileSet(fileSet)
							.archiveType(ArchiveType.TGZ)
							.urlTemplate("/linux/mongodb-linux-x86_64-ubuntu1804-{version}.tgz")
							.build())
					.build();

			PlatformMatchRule tools_ubuntu1804x64 = PlatformMatchRule.builder()
					.match(DistributionMatch.any(
											VersionRange.of("5.0.0", "5.0.2"),
											VersionRange.of("4.4.0", "4.4.9"),
											VersionRange.of("4.2.5", "4.2.16"),
											VersionRange.of("4.2.0", "4.2.3"),
											VersionRange.of("4.0.1", "4.0.26"),
											VersionRange.of("3.6.20", "3.6.22")
									)
									.andThen(PlatformMatch
											.withOs(OS.Linux)
											.withBitSize(BitSize.B64)
											.withCpuType(CPUType.X86)
											.withVersion(
													UbuntuVersion.UBUNTU_18_04, UbuntuVersion.UBUNTU_18_10, UbuntuVersion.UBUNTU_19_04, UbuntuVersion.UBUNTU_19_10,
													UbuntuVersion.UBUNTU_20_04, UbuntuVersion.UBUNTU_20_10
											)
									)
					)
					.finder(UrlTemplatePackageResolver.builder()
							.fileSet(fileSet)
							.archiveType(ArchiveType.TGZ)
							.urlTemplate("/tools/db/mongodb-database-tools-ubuntu1804-x86_64-{tools.version}.tgz")
							.build())
					.build();

    /*
      Ubuntu 20.04 ARM 64
      https://fastdl.mongodb.org/linux/mongodb-linux-aarch64-ubuntu2004-{}.tgz
      5.0.2 - 5.0.0, 4.4.9 - 4.4.0
    */
			PlatformMatchRule ubuntu2004arm = PlatformMatchRule.builder()
					.match(DistributionMatch.any(
											VersionRange.of("5.0.0", "5.0.2"),
											VersionRange.of("4.4.0", "4.4.9")
									)
									.andThen(PlatformMatch
											.withOs(OS.Linux)
											.withBitSize(BitSize.B64)
											.withCpuType(CPUType.ARM)
											.withVersion(UbuntuVersion.UBUNTU_20_04, UbuntuVersion.UBUNTU_20_10)
									)
					)
					.finder(UrlTemplatePackageResolver.builder()
							.fileSet(fileSet)
							.archiveType(ArchiveType.TGZ)
							.urlTemplate("/linux/mongodb-linux-aarch64-ubuntu2004-{version}.tgz")
							.build())
					.build();

			PlatformMatchRule tools_ubuntu2004arm = PlatformMatchRule.builder()
					.match(DistributionMatch.any(
											VersionRange.of("5.0.0", "5.0.2"),
											VersionRange.of("4.4.0", "4.4.9")
									)
									.andThen(PlatformMatch
											.withOs(OS.Linux)
											.withBitSize(BitSize.B64)
											.withCpuType(CPUType.ARM)
											.withVersion(UbuntuVersion.UBUNTU_20_04, UbuntuVersion.UBUNTU_20_10)
									)
					)
					.finder(UrlTemplatePackageResolver.builder()
							.fileSet(fileSet)
							.archiveType(ArchiveType.TGZ)
							.urlTemplate("/tools/db/mongodb-database-tools-ubuntu2004-arm64-{tools.version}.tgz")
							.build())
					.build();
    /*
      Ubuntu 20.04 x64
      https://fastdl.mongodb.org/linux/mongodb-linux-x86_64-ubuntu2004-{}.tgz
      5.0.2 - 5.0.0, 4.4.9 - 4.4.0
     */
			PlatformMatchRule ubuntu2004x64 = PlatformMatchRule.builder()
					.match(DistributionMatch.any(
											VersionRange.of("5.0.0", "5.0.2"),
											VersionRange.of("4.4.0", "4.4.9")
									)
									.andThen(PlatformMatch
											.withOs(OS.Linux)
											.withBitSize(BitSize.B64)
											.withCpuType(CPUType.X86)
											.withVersion(UbuntuVersion.UBUNTU_20_04, UbuntuVersion.UBUNTU_20_10)
									)
					)
					.finder(UrlTemplatePackageResolver.builder()
							.fileSet(fileSet)
							.archiveType(ArchiveType.TGZ)
							.urlTemplate("/linux/mongodb-linux-x86_64-ubuntu2004-{version}.tgz")
							.build())
					.build();

			PlatformMatchRule tools_ubuntu2004x64 = PlatformMatchRule.builder()
					.match(DistributionMatch.any(
											VersionRange.of("5.0.0", "5.0.2"),
											VersionRange.of("4.4.0", "4.4.9")
									)
									.andThen(PlatformMatch
											.withOs(OS.Linux)
											.withBitSize(BitSize.B64)
											.withCpuType(CPUType.X86)
											.withVersion(UbuntuVersion.UBUNTU_20_04, UbuntuVersion.UBUNTU_20_10)
									)
					)
					.finder(UrlTemplatePackageResolver.builder()
							.fileSet(fileSet)
							.archiveType(ArchiveType.TGZ)
							.urlTemplate("/tools/db/mongodb-database-tools-ubuntu2004-x86_64-{tools.version}.tgz")
							.build())
					.build();

			switch (command) {
					case MongoDump:
					case MongoImport:
					case MongoRestore:
							return PlatformMatchRules.empty()
									.withRules(
											tools_ubuntu2004arm, tools_ubuntu2004x64,
											tools_ubuntu1804arm, tools_ubuntu1804x64
									);
			}

    return PlatformMatchRules.empty()
            .withRules(
                    ubuntu2004arm, ubuntu2004x64,
                    ubuntu1804arm, ubuntu1804x64
            );
  }
}
