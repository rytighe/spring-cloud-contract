/*
 *  Copyright 2013-2016 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.springframework.cloud.contract.stubrunner.provider.moco

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.StubFinder
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.context.annotation.Configuration
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * @author Marcin Grzejszczak
 */
@ContextConfiguration(classes = Config, loader = SpringBootContextLoader)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner( ids =
		["com.example:fraudDetectionServerMoco"],
		repositoryRoot = "classpath:unpacked/")
@DirtiesContext
class MocoHttpServerStubSpec extends Specification {

	@Autowired StubFinder stubFinder

	def 'should successfully receive a response from a stub'() {
		expect:
			"${stubFinder.findStubUrl('fraudDetectionServerMoco').toString()}/name".toURL().text == 'fraudDetectionServerMoco'
	}

	@Configuration
	@EnableAutoConfiguration
	static class Config {
	}
}