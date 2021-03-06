/*
 * JumpVM: The Java Unified Multi Paradigm Virtual Machine.
 * Copyright (C) 2013 Tim Wiederhake
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */

package jumpvm.code.pama;

import jumpvm.ast.pama.PaMaAstNode;
import jumpvm.memory.objects.BasicValueObject;
import jumpvm.memory.objects.StackObject;

/**
 * Numerical subtraction.
 *
 * <pre>
 * STORE[SP - 1] := STORE[SP - 1] - STORE[SP];
 * SP := SP - 1;
 * </pre>
 */
public class SubInstruction extends BinaryOperationInstruction {
    /**
     * Create a new SubInstruction.
     *
     * @param sourceNode AstNode that is responsible for this instruction's creation
     */
    public SubInstruction(final PaMaAstNode sourceNode) {
        super(sourceNode);
    }

    @Override
    protected final StackObject execute(final int lhs, final int rhs) {
        return new BasicValueObject(lhs - rhs, null, String.valueOf(lhs) + " - " + String.valueOf(rhs));
    }

    @Override
    public final String getDisplayHoverText() {
        return "Numerical subtraction";
    }

    @Override
    public final String getMnemonic() {
        return "sub";
    }
}
