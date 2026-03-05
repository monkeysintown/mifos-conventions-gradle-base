/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifos.conventions.gradle.base.core.extension;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.model.ObjectFactory;

import javax.inject.Inject;

import static org.mifos.conventions.gradle.base.core.extension.MifosGradleExtensionProject.PROJECT_MAIN;

@Slf4j
public abstract class MifosGradleExtension {
    @Getter
    private final MifosGradleExtensionProject project;
    private final NamedDomainObjectContainer<MifosGradleExtensionDeveloper> developers;

    @Inject
    public MifosGradleExtension(ObjectFactory objects) {
        this.project = objects.newInstance(MifosGradleExtensionProject.class, PROJECT_MAIN);
        this.developers = objects.domainObjectContainer(MifosGradleExtensionDeveloper.class, name -> objects.newInstance(MifosGradleExtensionDeveloper.class, name));
    }

    public abstract NamedDomainObjectContainer<MifosGradleExtensionDeveloper> getDevelopers();

    public void project(Action<? super MifosGradleExtensionProject> action) {
        action.execute(this.project);
    }
}
