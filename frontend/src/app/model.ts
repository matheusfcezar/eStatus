export class Usuario {
    id: number;
    nome: string;
    email: string;
    dataCadastro: Date;
    dataAtivacao: Date;
    password: string;
    oab: string;
    advogado: boolean;
    ativo: boolean;
}


export class Processo {
    id: number;
    numero: string;
    dataCadastro: Date;
    dataEncerramento: Date;
    natureza: string;
    tipoProcesso: string;
    foro: Foro;
}

export class Foro {
    juiz: string;
    cidade: string;
    vara: string;
    descricao: string;
}
