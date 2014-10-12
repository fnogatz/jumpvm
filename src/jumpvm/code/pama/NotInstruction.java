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
import jumpvm.exception.ExecutionException;
import jumpvm.memory.objects.BasicValueObject;
import jumpvm.vm.PaMa;

/**
 * Logical not.
 *
 * <pre>
 * STORE[SP] := ! STORE[SP];
 * </pre>
 */
public class NotInstruction extends PaMaInstruction {
    /**
     * Create a new NotInstruction.
     *
     * @param sourceNode AstNode that is responsible for this instruction's creation
     */
    public NotInstruction(final PaMaAstNode sourceNode) {
        super(sourceNode);
    }

    @Override
    public final void execute(final PaMa vm) throws ExecutionException {
        final int value = vm.pop().getIntValue();
        if (value == 0) {
            vm.push(new BasicValueObject(1, "true", "true"));
        } else {
            vm.push(new BasicValueObject(0, "false", "false"));
        }
    }

    @Override
    public final String getDisplayHoverText() {
        return "Logical not";
    }

    @Override
    public final String getMnemonic() {
        return "not";
    }

    @Override
    public final String getParameter() {
        return null;
    }
}
