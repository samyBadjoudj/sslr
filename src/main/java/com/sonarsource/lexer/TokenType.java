/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */

package com.sonarsource.lexer;

import com.sonarsource.parser.ast.AstNodeType;

public interface TokenType extends AstNodeType {

  public String getName();

  public String getValue();

  public boolean hasToBeSkippedFromAst();

}
