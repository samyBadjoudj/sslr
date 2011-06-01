/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.sslr.dsl;

import static com.sonar.sslr.api.GenericTokenType.EOF;
import static com.sonar.sslr.api.GrammarFunctions.Standard.o2n;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Rule;

public abstract class CommandListDsl extends Grammar {

  public Rule translationUnit;
  public Rule command;

  public CommandListDsl() {
    translationUnit.is(o2n(command), EOF);
  }

  public final Rule getRootRule() {
    return translationUnit;
  }
}