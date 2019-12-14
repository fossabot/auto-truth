/*
 * Copyright 2019 Tatsuya Maki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.t28.auto.truth.compiler

import com.google.common.truth.Truth.assertThat
import com.google.testing.compile.JavaFileObjects.forSourceString
import com.google.testing.compile.JavaSourcesSubject.assertThat
import io.t28.auto.truth.AutoSubject
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import javax.lang.model.SourceVersion

object AutoTruthProcessorSpec : Spek({
    describe("Processor") {
        val processor = AutoTruthProcessor()

        describe("getSupportedSourceVersion") {
            it("should return latest source version") {
                // Act
                val actual = processor.supportedSourceVersion

                // Assert
                assertThat(actual)
                    .isEqualTo(SourceVersion.latestSupported())
            }
        }

        describe("getSupportedAnnotationTypes") {
            it("should return a set that contains @AutoSubject only") {
                // Act
                val actual = processor.supportedAnnotationTypes

                // Assert
                assertThat(actual)
                    .containsExactly("${AutoSubject::class.simpleName}")
            }
        }

        describe("process") {
            it("should compile without error") {
                // Arrange
                val subject = forSourceString(
                    "test.TestSubject",
                    """
                            package test;
                            
                            class TestSubject {
                            }
                        """.trimIndent())
                assertThat(subject)
                    .processedWith(processor)
                    .compilesWithoutError()
            }
        }
    }
})
