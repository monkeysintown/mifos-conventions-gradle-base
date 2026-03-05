/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifos.conventions.gradle.base.core;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.gradle.api.Plugin;
import org.gradle.api.UnknownDomainObjectException;
import org.gradle.api.initialization.Settings;
import org.gradle.api.publish.PublishingExtension;
import org.gradle.api.publish.VariantVersionMappingStrategy;
import org.gradle.api.publish.maven.MavenPublication;
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin;
import org.mifos.conventions.gradle.base.core.extension.MifosGradleExtension;

import java.nio.file.Files;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_EMPTY;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_EXTENSION;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_GROUP_ID;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_HOMEPAGE;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_LICENSE_NAME;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_LICENSE_URL;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_ROLE;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_SCM_GITHUB_GIT_URL;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_SCM_GITHUB_HTTP_URL;
import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_PROJECT_DEFAULT_TIMEZONE;

@Slf4j
public class MifosGradleBaseLayoutPlugin implements Plugin<Settings> {
    @Getter
    private MifosGradleExtension mifosExtension;

    @Override
    public void apply(Settings settings) {
        this.mifosExtension = settings.getExtensions().findByType(MifosGradleExtension.class);

        if(mifosExtension == null) {
            this.mifosExtension = settings.getExtensions().create(MIFOS_EXTENSION, MifosGradleExtension.class);
        }

        settings.getGradle().projectsLoaded(gradle -> {
            var rootProject = gradle.getRootProject();
            var mp = getMifosExtension().getProject();

            rootProject.getPlugins().apply("org.mifos.conventions.gradle.base.core.root");

            rootProject.afterEvaluate(project -> {
                // project.getTasks().findByName("mifosConfigUnzip").
                requireNonNull(project.getTasks().findByName("clean")).dependsOn(project.getTasks().getByName("mifosConfigUnzip"));
                requireNonNull(project.getTasks().findByName("build")).dependsOn(project.getTasks().getByName("mifosConfigUnzip"));
            });

            if (mp != null) {
                rootProject.setGroup(mp.getGroupId().getOrElse(MIFOS_PROJECT_DEFAULT_GROUP_ID));
                rootProject.setDescription(mp.getDescription().getOrElse(MIFOS_EMPTY));

                rootProject.subprojects(subproject -> {
                    subproject.afterEvaluate(sp -> {
                        sp.setVersion(rootProject.getVersion());
                        sp.getPlugins().apply(MavenPublishPlugin.class);

                        sp.getExtensions().configure(PublishingExtension.class, publishing -> {
                            sp.getPlugins().apply(MavenPublishPlugin.class);

                            publishing.publications(publications -> publications.create("mavenJava", MavenPublication.class, publication -> {
                                publication.versionMapping(versionMapping -> versionMapping.usage("java-api", variantVersionMappingStrategy -> variantVersionMappingStrategy.fromResolutionOf("runtimeClasspath")));
                                publication.versionMapping(versionMapping -> versionMapping.usage("java-runtime", VariantVersionMappingStrategy::fromResolutionResult));

                                try {
                                    var javaComponent = sp.getComponents().getByName("java");
                                    publication.from(javaComponent);
                                } catch (UnknownDomainObjectException _) {
                                    // ignore
                                }

                                publication.setGroupId(mp.getGroupId().getOrElse(MIFOS_PROJECT_DEFAULT_GROUP_ID));

                                publication.pom(pom -> {
                                    pom.getUrl().set(mp.getHomepage().getOrElse(MIFOS_PROJECT_DEFAULT_HOMEPAGE));

                                    // license
                                    pom.licenses(licenses -> licenses.license(license -> {
                                        license.getName().set(mp.getLicenseName().getOrElse(MIFOS_PROJECT_DEFAULT_LICENSE_NAME));
                                        license.getUrl().set(mp.getLicenseUrl().getOrElse(MIFOS_PROJECT_DEFAULT_LICENSE_URL));
                                    }));

                                    pom.developers(developers -> mifosExtension.getDevelopers().forEach(mifosDeveloper -> developers.developer(dev -> {
                                        dev.getId().set(mifosDeveloper.getName());
                                        dev.getName().set(mifosDeveloper.getFirstname().orElse(MIFOS_EMPTY).flatMap(firstname -> mifosDeveloper.getLastname().map(lastname -> (firstname + " " + lastname).trim()).orElse(MIFOS_EMPTY)));
                                        dev.getEmail().set(mifosDeveloper.getEmail().orElse(MIFOS_EMPTY));
                                        dev.getUrl().set(mifosDeveloper.getUrl().orElse(MIFOS_PROJECT_DEFAULT_SCM_GITHUB_HTTP_URL + "/" + mifosDeveloper.getName()));
                                        dev.getTimezone().set(mifosDeveloper.getTimezone().orElse(MIFOS_PROJECT_DEFAULT_TIMEZONE));
                                        dev.getOrganization().set(mifosDeveloper.getOrganisation().orElse(mp.getVendor()));
                                        dev.getOrganizationUrl().set(mifosDeveloper.getOrganisationUrl().orElse(mp.getHomepage()));
                                        dev.getRoles().set(mifosDeveloper.getRoles().orElse(List.of(MIFOS_PROJECT_DEFAULT_ROLE)));
                                    })));

                                    // scm
                                    pom.scm(scm -> {
                                        scm.getUrl().set(mp.getScmUrl().orElse(MIFOS_PROJECT_DEFAULT_SCM_GITHUB_HTTP_URL + "/" + requireNonNull(mifosExtension).getProject().getOrganisationId().get() + "/" + settings.getRootProject().getName()));
                                        scm.getConnection().set(mp.getScmConnectionMain().orElse(MIFOS_PROJECT_DEFAULT_SCM_GITHUB_GIT_URL + "/" + requireNonNull(mifosExtension).getProject().getOrganisationId().get() + "/" + settings.getRootProject().getName() + ".git"));
                                        scm.getDeveloperConnection().set(mp.getScmConnectionDeveloper().orElse(scm.getConnection()));
                                    });
                                });
                            }));

                            publishing.publications(publications -> publications.create("pluginMaven", MavenPublication.class, publication -> {
                                publication.setGroupId(mp.getGroupId().getOrElse(MIFOS_PROJECT_DEFAULT_GROUP_ID));

                                publication.pom(pom -> {
                                    pom.getUrl().set(mp.getHomepage().getOrElse(MIFOS_PROJECT_DEFAULT_HOMEPAGE));

                                    // license
                                    pom.licenses(licenses -> licenses.license(license -> {
                                        license.getName().set(mp.getLicenseName().getOrElse(MIFOS_PROJECT_DEFAULT_LICENSE_NAME));
                                        license.getUrl().set(mp.getLicenseUrl().getOrElse(MIFOS_PROJECT_DEFAULT_LICENSE_URL));
                                    }));

                                    pom.developers(developers -> mifosExtension.getDevelopers().forEach(mifosDeveloper -> developers.developer(dev -> {
                                        dev.getId().set(mifosDeveloper.getName());
                                        dev.getName().set(mifosDeveloper.getFirstname().orElse(MIFOS_EMPTY).flatMap(firstname -> mifosDeveloper.getLastname().map(lastname -> (firstname + " " + lastname).trim()).orElse(MIFOS_EMPTY)));
                                        dev.getEmail().set(mifosDeveloper.getEmail().orElse(MIFOS_EMPTY));
                                        dev.getUrl().set(mifosDeveloper.getUrl().orElse(MIFOS_PROJECT_DEFAULT_SCM_GITHUB_HTTP_URL + "/" + mifosDeveloper.getName()));
                                        dev.getTimezone().set(mifosDeveloper.getTimezone().orElse(MIFOS_PROJECT_DEFAULT_TIMEZONE));
                                        dev.getOrganization().set(mifosDeveloper.getOrganisation().orElse(mp.getVendor()));
                                        dev.getOrganizationUrl().set(mifosDeveloper.getOrganisationUrl().orElse(mp.getHomepage()));
                                        dev.getRoles().set(mifosDeveloper.getRoles().orElse(List.of(MIFOS_PROJECT_DEFAULT_ROLE)));
                                    })));

                                    // scm
                                    pom.scm(scm -> {
                                        scm.getUrl().set(mp.getScmUrl().orElse(MIFOS_PROJECT_DEFAULT_SCM_GITHUB_HTTP_URL + "/" + requireNonNull(mifosExtension).getProject().getOrganisationId().get() + "/" + settings.getRootProject().getName()));
                                        scm.getConnection().set(mp.getScmConnectionMain().orElse(MIFOS_PROJECT_DEFAULT_SCM_GITHUB_GIT_URL + "/" + requireNonNull(mifosExtension).getProject().getOrganisationId().get() + "/" + settings.getRootProject().getName() + ".git"));
                                        scm.getDeveloperConnection().set(mp.getScmConnectionDeveloper().orElse(scm.getConnection()));
                                    });
                                });
                            }));

                            publishing.repositories(repositories -> repositories.maven(maven -> {
                                maven.setName("staging-deploy");
                                maven.setUrl(sp.getLayout().getBuildDirectory().dir("staging-deploy"));
                            }));
                        });
                    });
                });

                try {
                    var rootPath = rootProject.getRootDir().toPath();

                    if(!Files.exists(rootPath.resolve(".gitignore"))) {
                        FileUtils.copyInputStreamToFile(requireNonNull(MifosGradleBaseLayoutPlugin.class.getClassLoader().getResourceAsStream("gitignore.txt")), rootPath.resolve(".gitignore").toFile());
                    }
                    if(!Files.exists(rootPath.resolve(".gitattributes"))) {
                        FileUtils.copyInputStreamToFile(requireNonNull(MifosGradleBaseLayoutPlugin.class.getClassLoader().getResourceAsStream("gitattributes.txt")), rootPath.resolve(".gitattributes").toFile());
                    }
                } catch (Exception ioe) {
                    log.error("Unable to locate .gitignore file", ioe);
                }
            } else {
                log.error("Mifos project definition not found!");
            }
        });

        settings.dependencyResolutionManagement(resolutionManagement -> resolutionManagement.repositories(repositories -> {
            repositories.mavenLocal();
            repositories.mavenCentral();
            repositories.gradlePluginPortal();
            repositories.maven(repository -> {
                repository.setUrl("https://central.sonatype.com/repository/maven-snapshots");
            });
            repositories.maven(repository -> {
                repository.setUrl("https://mifos.jfrog.io/artifactory/mifosx-gradle-local");
            });
        }));
        settings.getBuildscript().repositories(repositories -> {
            repositories.mavenLocal();
            repositories.mavenCentral();
            repositories.gradlePluginPortal();
            repositories.maven(repository -> {
                repository.setUrl("https://central.sonatype.com/repository/maven-snapshots");
            });
            repositories.maven(repository -> {
                repository.setUrl("https://mifos.jfrog.io/artifactory/mifosx-gradle-local");
            });
        });

        settings.getBuildscript().configurations(configurations -> configurations.configureEach(configuration -> {
            configuration.resolutionStrategy(resolutionStrategy -> resolutionStrategy.dependencySubstitution(dependencySubstitutions -> dependencySubstitutions.substitute(dependencySubstitutions.module("com.burgstaller:okhttp-digest:1.10"))
                    .using(dependencySubstitutions.module("io.github.rburgst:okhttp-digest:1.21"))
                    .because("okhttp-digest only version 1.21 is available on Maven Central. Old version was on JCenter, which asciidoctor-gradle-plugin depends on transitively through simplified-jruby-gradle-plugin via http-builder-ng-okhttp")));
        }));

        settings.getBuildscript().getConfigurations().forEach(configuration -> configuration.getResolutionStrategy().force("org.eclipse.jgit:org.eclipse.jgit:6.10.1.202505221210-r"));
    }
}
