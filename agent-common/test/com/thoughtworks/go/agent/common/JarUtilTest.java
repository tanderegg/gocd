/*
 * Copyright 2016 ThoughtWorks, Inc.
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
package com.thoughtworks.go.agent.common.util;

import com.thoughtworks.go.agent.common.util.JarUtil;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;

import org.apache.commons.io.FileUtils;

public class JarUtilTest {

  private static final String PATH_WITH_HASHES = "#hashes#in#path/";
  
  @Before
  public void setUp() throws IOException {
      FileUtils.copyFile(new File("testdata/test-agent.jar"), new File(PATH_WITH_HASHES + "test-agent.jar"));
  }

  @After
  public void tearDown() throws Exception {
      FileUtils.deleteQuietly(new File(Downloader.AGENT_LAUNCHER));
  }

  @Test
  public void shouldNotThrowMalformedUrlException() throws Exception {
    String absolutePath =  new File(PATH_WITH_HASHES + "test-agent.jar").getAbsolutePath();

    try {
      Object agent_launcher = JarUtil.objectFromJar(absolutePath, "Go-Agent-Launcher-Class");
    } catch (Exception e) {
      assertThat(e, not(instanceOf(MalformedURLException.class)));
    }
  }
}
