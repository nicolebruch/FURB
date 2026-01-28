professores_de_aluno(carla, P), write(P), nl, fail.

tem_disciplinas_em_comum(joao, pedro).

horarios_compativeis(maria, pedro).

matriculado(A1, redes), matriculado(A2, redes), A1 @< A2, write(A1), write(' - '), write(A2), nl, fail.

matriculado(A1, logica), matriculado(A2, logica), matriculado(A3, logica), A1 @< A2, A2 @< A3, write(A1), write(' - '), write(A2), write(' - '), write(A3), nl, fail.

disciplinas_em_comum(lucas, ana, D), write(D), nl, fail.

disponibilidade(A, segunda_tarde), write(A), nl, fail.

matriculado(A, inteligencia_artificial), write(A), nl, fail.