/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifos.conventions.gradle.base.core;

import lombok.extern.slf4j.Slf4j;
import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.file.FileVisitDetails;
import org.gradle.api.initialization.Settings;
import org.gradle.api.model.ObjectFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.mifos.conventions.gradle.base.core.MifosGradleConstants.MIFOS_BUILD_FOLDER_PATTERN;

@Slf4j
public final class MifosGradleUtils {
    private MifosGradleUtils() {}

    public static Optional<String> include(Settings settings, String projectIdMiddle, String projectIdSuffix, String fileEndsWith, Path file) {
        String projectId = null;

        if(Files.isRegularFile(file) && file.endsWith(fileEndsWith)) {
            projectId = settings.getRootProject().getName() + "-" + projectIdMiddle + projectIdSuffix;

            settings.include(":" + projectId);
            settings.project(":" + projectId).setProjectDir(file.getParent().toFile());
        }

        return Optional.ofNullable(projectId);
    }

    public static Path getParent(Path path, int level) {
        var p = path;

        for(int i=0; i < level; i++) {
            p = p.getParent();
        }

        return p;
    }

    public static void visit(ObjectFactory objects, Path path, Action<? super FileVisitDetails> visitor, String... includes) {
        var fileTree = objects.fileTree();
        fileTree.from(path);
        fileTree.include(includes);
        fileTree.exclude(MIFOS_BUILD_FOLDER_PATTERN);

        fileTree.visit(visitor);
    }

    public static boolean hasProject(Project project, String configuration, String id) {
        return project.getConfigurations().getByName(configuration).getAllDependencies().stream().anyMatch(dependency -> dependency.getName().equals(id));
    }
}
