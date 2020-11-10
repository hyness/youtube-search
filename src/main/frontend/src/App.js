import React from "react";
import { useForm } from "react-hook-form";
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from "yup";
import TextField from "@material-ui/core/TextField";
import IconButton from "@material-ui/core/IconButton";
import SearchIcon from "@material-ui/icons/Search";
import {Container} from "@material-ui/core";


const schema = yup.object().shape({
    term: yup.string().required()
});

export default function App() {
    const { register, handleSubmit, errors } = useForm({
        resolver: yupResolver(schema)
    });

    const onSubmit = data => console.log(data);

    return (
        <Container maxWidth={"lg"}>
            <form onSubmit={handleSubmit(onSubmit)}>
                <TextField name="term" inputRef={register} />

                <IconButton aria-label="search" type={"submit"}>
                    <SearchIcon />
                </IconButton>

                <p>{errors.term?.message}</p>

            </form>
        </Container>
    );
}
