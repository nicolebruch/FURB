professor(alice).
professor(bob).
professor(clara).

aluno(joao).
aluno(maria).
aluno(carla).
aluno(pedro).
aluno(ana).
aluno(lucas).

leciona(alice,logica).
leciona(alice,inteligencia_artificial).
leciona(bob,redes).
leciona(clara,programacao).
leciona(clara,banco_de_dados).

estuda(joao,logica).
estuda(joao,redes).
estuda(maria,inteligencia_artificial).
estuda(maria,banco_de_dados).
estuda(carla,logica).
estuda(carla,inteligencia_artificial).
estuda(pedro,redes).
estuda(pedro,banco_de_dados).
estuda(ana,inteligencia_artificial).
estuda(ana,programacao).
estuda(lucas,logica).
estuda(lucas,programacao).

disponibilidade(joao,segunda_tarde).
disponibilidade(maria,quarta_manha).
disponibilidade(carla,segunda_tarde).
disponibilidade(pedro,quarta_manha).
disponibilidade(ana,segunda_tarde).
disponibilidade(lucas,segunda_tarde).

matriculado(A, D) :-
    estuda(A, D).

regra1(Aluno, Professor) :-
    matriculado(Aluno, Disciplina),
    leciona(Professor, Disciplina).

professores_de_aluno(Aluno, Professor) :-
    regra1(Aluno, Professor).

disciplinas_em_comum(Aluno1, Aluno2, Disciplina) :-
    Aluno1 \= Aluno2,
    matriculado(Aluno1, Disciplina),
    matriculado(Aluno2, Disciplina).

tem_disciplinas_em_comum(Aluno1, Aluno2) :-
    disciplinas_em_comum(Aluno1, Aluno2, _).

horarios_compativeis(Aluno1, Aluno2) :-
    Aluno1 \= Aluno2,
    disponibilidade(Aluno1, Horario),
    disponibilidade(Aluno2, Horario).

ordena_par(A, B) :-
    A @< B.

grupo2(Aluno1, Aluno2, Professor, Disciplina, Horario) :-
    Aluno1 \= Aluno2,
    matriculado(Aluno1, Disciplina),
    matriculado(Aluno2, Disciplina),
    disponibilidade(Aluno1, Horario),
    disponibilidade(Aluno2, Horario),
    leciona(Professor, Disciplina),
    ordena_par(Aluno1, Aluno2).