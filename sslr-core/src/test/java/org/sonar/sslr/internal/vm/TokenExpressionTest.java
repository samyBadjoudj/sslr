/*
 * SonarSource Language Recognizer
 * Copyright (C) 2010 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.sslr.internal.vm;

import com.sonar.sslr.api.GenericTokenType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.sonar.sslr.internal.matchers.MatcherContext;
import org.sonar.sslr.internal.matchers.TokenMatcher;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class TokenExpressionTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void should_compile() {
    TokenExpression expression = new TokenExpression(GenericTokenType.IDENTIFIER, new SubExpression(1, 2));
    Instruction[] instructions = expression.compile();
    assertThat(instructions).isEqualTo(new Instruction[] {
      Instruction.call(2, expression),
      Instruction.jump(4),
      SubExpression.mockInstruction(1),
      SubExpression.mockInstruction(2),
      Instruction.ret()
    });
  }

  @Test
  public void should_implement_Matcher() {
    TokenExpression expression = new TokenExpression(GenericTokenType.IDENTIFIER, mock(ParsingExpression.class));
    // Important for AstCreator
    assertThat(expression).isInstanceOf(TokenMatcher.class);
    assertThat(expression.getTokenType()).isSameAs(GenericTokenType.IDENTIFIER);
    thrown.expect(UnsupportedOperationException.class);
    expression.match(mock(MatcherContext.class));
  }

}